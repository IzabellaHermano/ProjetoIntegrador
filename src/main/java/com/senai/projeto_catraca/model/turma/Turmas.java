package com.senai.projeto_catraca.model.turma;

import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public class Turmas {
    private int id;
    private String nome;
    private String turno;
    private int professorId;  // Id do professor responsável
    private List<Aluno> matriculasAlunos = new ArrayList<>();  // IDs de alunos associados à turma

    public Turmas(String nome, String turno, int professorId) {
        this.nome = nome;
        this.turno = turno;
        this.professorId = professorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public List<Aluno> getMatriculasAlunos() {
        return matriculasAlunos;
    }

    public void setMatriculasAlunos(List<Aluno> matriculasAlunos) {
        this.matriculasAlunos = matriculasAlunos;
    }

    public void adicionarAluno(Aluno aluno) {
        if (!matriculasAlunos.contains(aluno)) {
            matriculasAlunos.add(aluno);
        }
    }

    public void removerAluno(int matricula) {
        matriculasAlunos.remove(Integer.valueOf(matricula));
    }
}
