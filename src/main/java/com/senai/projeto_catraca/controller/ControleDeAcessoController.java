package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.usuario.aluno.Aluno;
import com.senai.projeto_catraca.model.usuario.aluno.AlunoDAO;
import com.senai.projeto_catraca.model.dao.json.ProfessorDAO;
import com.senai.projeto_catraca.model.turma.horario.HorarioBase;
import com.senai.projeto_catraca.model.turma.horario.HorarioBaseDAO;
import com.senai.projeto_catraca.model.usuario.Professor;
import com.senai.projeto_catraca.websocket.WebSocketSender;

import java.util.Optional;

public class ControleDeAcessoController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final HorarioBaseDAO horarioDAO = new HorarioBaseDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public String processarEntrada(String rfid) {
        Optional<Aluno> alunoOpt = alunoDAO.buscarPorRfid(rfid);
        if (alunoOpt.isEmpty()) {
            return "[ACESSO NEGADO] Aluno não encontrado para RFID: " + rfid;
        }

        Aluno aluno = alunoOpt.get();
        Optional<HorarioBase> horarioOpt = horarioDAO.buscarHorarioDoAluno(aluno.getId());

        if (horarioOpt.isEmpty()) {
            return "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhum horário atribuído.";
        }

        HorarioBase horario = horarioOpt.get();
        boolean atrasado = aluno.Atraso(horario.getHoraInicio());

        if (atrasado) {
            Optional<Professor> professorOpt = professorDAO.buscarPorId(horario.getIdProfessor());
            professorOpt.ifPresent(professor -> {
                String msg = "[ATRASO] Aluno " + aluno.getNome() + " chegou atrasado.";
                WebSocketSender.enviarMensagem(msg);
            });
            return "[ATRASO DETECTADO] Aluno: " + aluno.getNome();
        }

        return "[ENTRADA AUTORIZADA] Aluno: " + aluno.getNome();
    }
}


