package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class Curso extends ArrayList<Curso> {
    private String nome; // Nome do curso //
    private int id; // Identificador unico do curso //
    private int duracao; // Duração do curso em meses //

    public Curso(String nome, int id, int duracao) {
        this.nome = nome;
        this.id = id;
        this.duracao = duracao;
    }
    // Getters e Setters //

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
}

