package com.senai.projeto_catraca.model.usuario;

public class Professor extends Usuario {
    private String disciplina;

    public Professor(String nome, String senha, String CPF, int id, String endereco, String telefone, String disciplina) {
        super(nome,senha,CPF,id,endereco,telefone);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}