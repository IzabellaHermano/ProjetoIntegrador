package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.dao.json.ProfessorDAO;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.turma.TurmasDAO;
import com.senai.projeto_catraca.model.turma.horario.HorarioBase;
import com.senai.projeto_catraca.model.turma.horario.HorarioBaseDAO;
import com.senai.projeto_catraca.model.usuario.Professor;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;
import com.senai.projeto_catraca.model.usuario.aluno.AlunoDAO;
import com.senai.projeto_catraca.websocket.WebSocketSender;

import java.util.Optional;

public class OcorrenciaController {
<<<<<<< HEAD
}
=======
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final HorarioBaseDAO horarioDAO = new HorarioBaseDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final TurmasDAO turmasDAO = new TurmasDAO();

    public String gerarOcorrenciaAtraso(String rfid) {
        Optional<Aluno> alunoOpt = alunoDAO.buscarPorRfid(rfid);
        Optional<Turmas> turmasOpt = turmasDAO.buscarTurmaPorId(5);

        if (alunoOpt.isEmpty()) {
            return "[ACESSO NEGADO] Aluno não encontrado para RFID: " + rfid;
        }
        if (turmasOpt.isEmpty()) {
            return "[ACESSO NEGADO] Turma não encontrada.";
        }

        Aluno aluno = alunoOpt.get();
        Turmas turma = turmasOpt.get();
        Optional<HorarioBase> horarioOpt = horarioDAO.buscarHorarioDoAluno(aluno.getId());

        if (horarioOpt.isEmpty()) {
            return "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhum horário atribuído.";
        }

        // Aqui comparamos o horário da turma com o limite de 08:00
        String horarioEntrada = turma.getHorarioEntrada(); // Ex: "08:15"
        int entradaMinutos = converterHorarioParaMinutos(horarioEntrada);
        int limiteMinutos = converterHorarioParaMinutos("08:00");

        boolean atrasado = entradaMinutos > limiteMinutos;

        if (atrasado) {
            Optional<Professor> professorOpt = professorDAO.buscarPorId(horarioOpt.get().getIdProfessor());
            professorOpt.ifPresent(professor -> {
                String msg = "[ATRASO] Aluno " + aluno.getNome() + " chegou atrasado.";
                WebSocketSender.enviarMensagem(msg);
            });
            return "[ATRASO DETECTADO] Aluno: " + aluno.getNome();
        }

        return "[ENTRADA AUTORIZADA] Aluno: " + aluno.getNome();
    }
    private int converterHorarioParaMinutos(String horario) {
        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return horas * 60 + minutos;
    }


    public String gerarOcorrenciaSaida(){
        return "";
    }

}
>>>>>>> 5e588ac9484caf9b6fba8eb42073efe2ee773e53
