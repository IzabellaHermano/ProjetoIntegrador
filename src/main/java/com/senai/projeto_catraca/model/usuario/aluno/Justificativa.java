package com.senai.projeto_catraca.model.usuario.aluno;

import java.io.File;
import java.time.LocalDate;

public class Justificativa {
    private String tipo;
    private String descricao;
    private LocalDate data;
    private String anexo;
    private int id;

    public Justificativa(String tipo, String anexo, LocalDate data, String descricao, int id) {
        this.tipo = tipo;
        this.anexo = anexo;
        this.data = data;
        this.descricao = descricao;
        this.id = id;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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
