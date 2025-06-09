package com.senai.projeto_catraca.model.usuario.aluno;

public class Justificativa {
    private String tipo;
    private String descricao;
    private String data;
    private String anexo;
    private String status;
    private int id;

    public Justificativa(String tipo, String anexo, String data, String descricao, int id, String status) {
        this.tipo = tipo;
        this.anexo = anexo;
        this.data = data;
        this.descricao = descricao;
        this.id = id;
        this.status = status;
    }

    public Justificativa(String anexo) {
        this.anexo = anexo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}