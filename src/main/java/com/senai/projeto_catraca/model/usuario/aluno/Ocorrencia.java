package com.senai.projeto_catraca.model.usuario.aluno;

public class Ocorrencia {
    private int AlunoID;
    private int JustificativaID;
    private String dataHora;

    public Ocorrencia(int alunoID, int justificativaID, String dataHora) {
        AlunoID = alunoID;
        JustificativaID = justificativaID;
        this.dataHora = dataHora;
    }

    public int getAlunoID() {
        return AlunoID;
    }

    public void setAlunoID(int alunoID) {
        AlunoID = alunoID;
    }

    public int getJustificativaID() {
        return JustificativaID;
    }

    public void setJustificativaID(int justificativaID) {
        JustificativaID = justificativaID;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
