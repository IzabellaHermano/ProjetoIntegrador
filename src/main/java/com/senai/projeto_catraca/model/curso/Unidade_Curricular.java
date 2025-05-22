package com.senai.projeto_catraca.model.curso;

public class Unidade_Curricular {
        private int id; // Identificador //
        private String nome; // Nome da Unidade Curricular //
        private int cargaHoraria; // Carga horária total //
        private int idCurso; // ID do curso pertencente //

        // Construtor //

    public Unidade_Curricular(int id, String nome, int cargaHoraria, int idCurso) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.idCurso = idCurso;
    }
      // Getters e Setters //

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
}
