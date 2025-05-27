package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class Curso extends ArrayList<Curso> {
    private String nome;
    private int id;
    private int duracao;

    public Curso() {
        this.nome = nome;
        this.id = id;
        this.duracao = duracao;
    }

    public Curso(String nomeCurso) {
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
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Curso: " + nome;
    }
}

