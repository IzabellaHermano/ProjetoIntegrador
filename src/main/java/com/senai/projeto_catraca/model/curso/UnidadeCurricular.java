package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class UnidadeCurricular {
    private int id;
    private String nome;
    private String cargaHoraria;
    private ArrayList<String> listaProfessores;

    public UnidadeCurricular(int id, ArrayList<String> listaProfessores, String cargaHoraria, String nome) {
        this.id = id;
        this.listaProfessores = listaProfessores;
        this.cargaHoraria = cargaHoraria;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList getListaProfessores() {
        return listaProfessores;
    }

    public void setListaProfessores(ArrayList listaProfessores) {
        this.listaProfessores = listaProfessores;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

