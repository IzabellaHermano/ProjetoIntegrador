package com.senai.projeto_catraca.model.usuario.aluno;

import java.time.LocalDate;

public class Justificativa {
    private int id;
    private String tipo;
    private String descricao;
    private LocalDate data;
    private String anexo;

    public Justificativa(int id, String tipo, String descricao, LocalDate data, String anexo) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.anexo = anexo;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public void setId(int id) {
        this.id = id;
    }
}
