package com.senai.projeto_catraca.model.turma.horario;

import java.sql.Date;
import java.time.LocalTime;

public class HorarioBase {
    private int id;
    private int idProfessor;
    private int idTurma;
    private LocalTime horario;

    public HorarioBase(int id, int idProfessor, int idTurma, LocalTime horario) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idTurma = idTurma;
        this.horario = horario;
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

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalTime getHoraInicio() {
        return horario != null ? horario : LocalTime.of(8, 0); // Valor padrão se for null
    }
}
