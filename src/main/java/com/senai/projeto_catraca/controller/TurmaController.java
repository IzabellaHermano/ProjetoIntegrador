package com.senai.projeto_catraca.controller;

import java.util.ArrayList;
import java.util.List;

import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.turma.TurmasDAO;

public class TurmaController {

    private final TurmasDAO turmasDAO = new TurmasDAO();
    public String cadastrarTurma(String nome, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo) {
        turmasDAO.inserir(new Turmas(0, nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, 0));
        return "Turma cadastrada com sucesso.";
    }


    public String atualizarTurma(int id, String nome, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, int subTurmas) {
        turmasDAO.atualizar(new Turmas(id, nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, id));
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