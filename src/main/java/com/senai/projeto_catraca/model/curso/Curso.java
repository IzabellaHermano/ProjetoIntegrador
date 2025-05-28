package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class Curso {
    private String nome;
    private int id;
    private int duracao;
    private String tipo;
    private ArrayList<String> unidadeCurricular;

    public Curso(ArrayList<String> unidadeCurricular, int id) {
        this.unidadeCurricular = unidadeCurricular;
        this.id = id;
    }

    public Curso(String nome, String tipo, int duracao, int id, ArrayList<String> unidadeCurricular) {
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

    public ArrayList<String> getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(ArrayList<String> unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }
}

