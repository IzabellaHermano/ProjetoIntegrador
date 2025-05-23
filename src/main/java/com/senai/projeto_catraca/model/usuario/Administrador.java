package com.senai.projeto_catraca.model.usuario;

public class Administrador extends Usuario {
    private String permissao;
    public Administrador(String nome, String senha, String CPF, int id, String endereco, String telefone,String permissao) {
        super(nome, senha, CPF, id, endereco, telefone);
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    @Override
    public String getTipo() {
        return "Administrador";
    }
}
