package com.senai.projeto_catraca.model.turma;

import com.senai.projeto_catraca.model.curso.Semestre;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public class SubTurma {
    private int id;
    private List<String> alunos = new ArrayList<>();

    public SubTurma(int id, List<String> alunos) {
        this.id = id;
        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<String> alunos) {
        this.alunos = alunos;
    }
}
