package com.senai.projeto_catraca.model.turma;

import com.senai.projeto_catraca.model.curso.Semestre;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public class SubTurma {
    private int id;
    private int alunos;

    public SubTurma(int id, int alunos) {
        this.id = id;
        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunos() {
        return alunos;
    }

    public void setAlunos(int alunos) {
        this.alunos = alunos;
    }
}
