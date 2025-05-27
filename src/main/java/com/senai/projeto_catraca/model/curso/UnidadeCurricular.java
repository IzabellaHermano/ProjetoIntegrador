package com.senai.projeto_catraca.model.curso;

public class UnidadeCurricular {
    private int id;
    private String nome;
    private int cargaHoraria;
    private int idCurso;

    public UnidadeCurricular(String nome) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.idCurso = idCurso;
    }

    public UnidadeCurricular(String nomeUC, int idCurso) {
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
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    public int getIdCurso() {
        return idCurso;
    }
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        return "UnidadeCurricular" + nome;
    }
}

