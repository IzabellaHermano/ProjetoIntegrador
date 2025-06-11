package com.senai.projeto_catraca.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.senai.projeto_catraca.model.dao.json.ProfessorDAO;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.turma.TurmasDAO;
import com.senai.projeto_catraca.model.turma.horario.HorarioBase;
import com.senai.projeto_catraca.model.turma.horario.HorarioBaseDAO;
import com.senai.projeto_catraca.model.usuario.Professor;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;
import com.senai.projeto_catraca.model.usuario.aluno.AlunoDAO;
import com.senai.projeto_catraca.model.usuario.aluno.Ocorrencia;
import com.senai.projeto_catraca.model.usuario.aluno.OcorrenciaDAO;
import com.senai.projeto_catraca.websocket.WebSocketSender;

public class OcorrenciaController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final HorarioBaseDAO horarioDAO = new HorarioBaseDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final TurmasDAO turmasDAO = new TurmasDAO();
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String HORARIO_LIMITE_ENTRADA = "08:00";

    // Métodos CRUD para gerenciar ocorrências
    public boolean adicionarOcorrencia(Ocorrencia ocorrencia) {
        try {
            ocorrenciaDAO.adicionar(ocorrencia);

            // Notificar via WebSocket sobre nova ocorrência
            String mensagem = String.format("[NOVA OCORRÊNCIA] ID: %d - Aluno ID: %d - %s",
                    ocorrencia.getId(), ocorrencia.getAlunoID(), ocorrencia.getDataHora());
            WebSocketSender.enviarMensagem(mensagem);

            return true;
        } catch (Exception e) {
            System.err.println("Erro ao adicionar ocorrência: " + e.getMessage());
            return false;
        }
    }

    public List<Ocorrencia> listarOcorrencias() {
        return ocorrenciaDAO.listar();
    }

    public Ocorrencia buscarOcorrenciaPorId(int id) {
        return ocorrenciaDAO.buscarPorId(id);
    }

    public boolean atualizarOcorrencia(Ocorrencia ocorrencia) {
        boolean sucesso = ocorrenciaDAO.atualizar(ocorrencia);

        if (sucesso) {
            // Notificar via WebSocket sobre atualização
            String mensagem = String.format("[OCORRÊNCIA ATUALIZADA] ID: %d - Aluno ID: %d",
                    ocorrencia.getId(), ocorrencia.getAlunoID());
            WebSocketSender.enviarMensagem(mensagem);
        }

        return sucesso;
    }

    public boolean removerOcorrencia(int id) {
        boolean sucesso = ocorrenciaDAO.deletar(id);

        if (sucesso) {
            // Notificar via WebSocket sobre remoção
            String mensagem = String.format("[OCORRÊNCIA REMOVIDA] ID: %d", id);
            WebSocketSender.enviarMensagem(mensagem);
        }

        return sucesso;
    }

    // Método melhorado para gerar ocorrência de atraso
    public String gerarOcorrenciaAtraso(String rfid) {
        try {
            Optional<Aluno> alunoOpt = alunoDAO.buscarPorRfid(rfid);

            if (alunoOpt.isEmpty()) {
                String mensagem = "[ACESSO NEGADO] RFID não encontrado: " + rfid;
                WebSocketSender.enviarMensagem(mensagem);
                return mensagem;
            }

            Aluno aluno = alunoOpt.get();
            Optional<Turmas> turmasOpt = turmasDAO.buscarTurmaPorId(5); // Assumindo que aluno tem turmaId

            if (turmasOpt.isEmpty()) {
                String mensagem = "[ERRO] Turma não encontrada para aluno: " + aluno.getNome();
                WebSocketSender.enviarMensagem(mensagem);
                return mensagem;
            }

            Turmas turma = turmasOpt.get();
            Optional<HorarioBase> horarioOpt = horarioDAO.buscarHorarioDoAluno(aluno.getId());

            if (horarioOpt.isEmpty()) {
                String mensagem = "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhum horário atribuído.";
                WebSocketSender.enviarMensagem(mensagem);
                return mensagem;
            }

            // Verificar se é atraso
            LocalDateTime agora = LocalDateTime.now();
            String horarioAtual = agora.format(DateTimeFormatter.ofPattern("HH:mm"));

            String horarioEntrada = turma.getHorarioEntrada();
            boolean atrasado = isAtraso(horarioAtual, horarioEntrada);

            if (atrasado) {
                // Criar ocorrência de atraso automaticamente
                Ocorrencia ocorrenciaAtraso = criarOcorrenciaAtraso(aluno, agora);
                adicionarOcorrencia(ocorrenciaAtraso);

                // Notificar professor
                Optional<Professor> professorOpt = professorDAO.buscarPorId(horarioOpt.get().getIdProfessor());
                if (professorOpt.isPresent()) {
                    String mensagemProfessor = String.format(
                            "[ATRASO] Aluno: %s (ID: %d) chegou às %s - Horário previsto: %s",
                            aluno.getNome(), aluno.getId(), horarioAtual, horarioEntrada
                    );
                    WebSocketSender.enviarMensagem(mensagemProfessor);
                }

                return String.format("[ATRASO DETECTADO] Aluno: %s - Ocorrência registrada", aluno.getNome());
            }

            // Entrada normal
            String mensagem = String.format("[ENTRADA AUTORIZADA] Aluno: %s às %s", aluno.getNome(), horarioAtual);
            WebSocketSender.enviarMensagem(mensagem);
            return mensagem;

        } catch (Exception e) {
            String mensagemErro = "[ERRO] Falha no processamento: " + e.getMessage();
            System.err.println(mensagemErro);
            WebSocketSender.enviarMensagem(mensagemErro);
            return mensagemErro;
        }
    }

    public String gerarOcorrenciaSaida(String rfid) {
        try {
            Optional<Aluno> alunoOpt = alunoDAO.buscarPorRfid(rfid);

            if (alunoOpt.isEmpty()) {
                String mensagem = "[SAÍDA NEGADA] RFID não encontrado: " + rfid;
                WebSocketSender.enviarMensagem(mensagem);
                return mensagem;
            }

            Aluno aluno = alunoOpt.get();
            LocalDateTime agora = LocalDateTime.now();
            String horarioAtual = agora.format(DateTimeFormatter.ofPattern("HH:mm"));

            String mensagem = String.format("[SAÍDA REGISTRADA] Aluno: %s às %s", aluno.getNome(), horarioAtual);
            WebSocketSender.enviarMensagem(mensagem);

            return mensagem;

        } catch (Exception e) {
            String mensagemErro = "[ERRO] Falha no registro de saída: " + e.getMessage();
            System.err.println(mensagemErro);
            WebSocketSender.enviarMensagem(mensagemErro);
            return mensagemErro;
        }
    }

    // Métodos auxiliares privados
    private boolean isAtraso(String horarioAtual, String horarioEntrada) {
        int atualMinutos = converterHorarioParaMinutos(horarioAtual);
        int entradaMinutos = converterHorarioParaMinutos(horarioEntrada);
        return atualMinutos > entradaMinutos;
    }

    private int converterHorarioParaMinutos(String horario) {
        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return horas * 60 + minutos;
    }

    private Ocorrencia criarOcorrenciaAtraso(Aluno aluno, LocalDateTime dataHora) {
        // Gerar ID único para a ocorrência (pode ser melhorado com UUID ou sequencial do banco)
        int novoId = gerarProximoId();

        return new Ocorrencia(
                novoId,
                aluno.getId(),
                1, // ID da justificativa para "atraso" - assumindo que existe
                dataHora.format(FORMATTER)
        );
    }

    private int gerarProximoId() {
        List<Ocorrencia> ocorrencias = listarOcorrencias();
        return ocorrencias.stream()
                .mapToInt(Ocorrencia::getId)
                .max()
                .orElse(0) + 1;
    }

    // Método para buscar ocorrências por aluno
    public List<Ocorrencia> buscarOcorrenciasPorAluno(int alunoId) {
        return ocorrenciaDAO.listar().stream()
                .filter(o -> o.getAlunoID() == alunoId)
                .toList();
    }

    // Método para contar atrasos de um aluno
    public long contarAtrasosPorAluno(int alunoId) {
        return buscarOcorrenciasPorAluno(alunoId).stream()
                .filter(o -> o.getJustificativaID() == 1) // Assumindo que 1 = atraso
                .count();
    }
}