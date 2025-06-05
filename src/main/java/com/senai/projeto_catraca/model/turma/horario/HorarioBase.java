package com.senai.projeto_catraca.model.turma.horario;

public class HorarioBase {
    private int id;
    private int idProfessor;
    private int idTurma;


    public HorarioBase(int id, int idProfessor, int idTurma) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idTurma = idTurma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
}
