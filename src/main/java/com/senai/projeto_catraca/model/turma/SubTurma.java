package com.senai.projeto_catraca.model.turma;

import com.senai.projeto_catraca.model.curso.Semestre;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public class SubTurma {
    private int id;
    private int idAlunos;

    public SubTurma(int id, int idAlunos) {
        this.id = id;
        this.idAlunos = idAlunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlunos() {
        return idAlunos;
    }

    public void setIdAlunos(int idAlunos) {
        this.idAlunos = idAlunos;
    }
}
