package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class Curso {
    private String nome;
    private int id;
    private int duracao;
    private String tipo;
    private ArrayList<UnidadeCurricular> unidadeCurricular;

    public Curso(ArrayList<UnidadeCurricular> unidadeCurricular, int id) {
        this.unidadeCurricular = unidadeCurricular;
        this.id = id;
    }

    public Curso(String nome, String tipo, int duracao, int id, ArrayList<UnidadeCurricular> unidadeCurricular) {
        this.nome = nome;
        this.tipo = tipo;
        this.duracao = duracao;
        this.id = id;
        this.unidadeCurricular = unidadeCurricular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public ArrayList<UnidadeCurricular> getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(ArrayList<UnidadeCurricular> unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }
}

