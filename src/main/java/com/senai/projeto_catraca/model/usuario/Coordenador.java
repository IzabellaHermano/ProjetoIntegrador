package com.senai.projeto_catraca.model.usuario;

public class Coordenador extends Usuario{
    public Coordenador(String nome, String senha, String CPF, int id, String endereco, String telefone) {
        super(nome, senha, CPF, id, endereco, telefone);
    }

    @Override
    public String getTipo() {
        return "Coordenador";
    }
}
