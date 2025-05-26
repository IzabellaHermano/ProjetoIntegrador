package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.turma.TurmasDAO;

import java.util.ArrayList;
import java.util.List;

public class TurmaController {

    private final TurmasDAO turmasDAO = new TurmasDAO();
    public String cadastrarTurma(String nome, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
        turmasDAO.inserir(new Turmas(0, nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, subTurmas));
        return "Turma cadastrada com sucesso.";
    }


    public String atualizarTurma(int id, String nome, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
        turmasDAO.inserir(new Turmas(id, nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, subTurmas));
        return "Turma atualizada com sucesso.";
    }

    public String removerHorario(int id) {
        turmasDAO.remover(id);
        return "Turma removida com sucesso.";
    }

    public Turmas[] listarTurmas() {
        return turmasDAO.listarTodos().toArray(new Turmas[0]);
    }

    public String cadastrarSubTurma(SubTurma subTurma) {
        List<SubTurma> subTurmas = new ArrayList<>();
        subTurmas.add(subTurma);
        return "Turma cadastrada com sucesso.";
    }

}