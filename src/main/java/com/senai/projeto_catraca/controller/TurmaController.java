package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.dao.json.TurmasDAO;

import java.util.ArrayList;
import java.util.List;

public class TurmaController {

    private final TurmasDAO turmasDAO = new TurmasDAO();
    public String cadastrarTurma(String nome, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
        turmasDAO.inserir(new Turmas(0, nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, subTurmas));
        return "Turma cadastrada com sucesso.";
    }


    public String atualizarTurma(int id, String nome, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
        turmasDAO.atualizar(new Turmas(id, nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, subTurmas));
        return "Turma atualizada com sucesso.";
    }

    public String removerTurma(int id) {
        turmasDAO.remover(id);
        return "Turma removida com sucesso.";
    }

    public Turmas[] listarTurmas() {
        return turmasDAO.listarTodos().toArray(new Turmas[0]);
    }

//    esse cadastrarSubTurma foi uma tentativa de fazer o codigo funcionar, no final nem precisei dele
//    talvez a gente usa mais tarde.

    public String cadastrarSubTurma(SubTurma subTurma) {
        List<SubTurma> subTurmas = new ArrayList<>();
        subTurmas.add(subTurma);
        return "Turma cadastrada com sucesso.";
    }

}