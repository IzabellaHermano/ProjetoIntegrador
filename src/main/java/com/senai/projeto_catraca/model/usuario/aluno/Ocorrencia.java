package com.senai.projeto_catraca.model.usuario.aluno;

public class Ocorrencia {
    private int id;
    private int AlunoID;
    private int JustificativaID;
    private String dataHora;
    private String status;

    public Ocorrencia(int id, int alunoID, int justificativaID, String dataHora) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD

=======
>>>>>>> 95f1158cc6ef49de8044e73c834fefc3261e60ec
    public String getStatus() {
        return status;
    }

<<<<<<< HEAD

    public void setStatus(String status) {
        this.status = status;
    }
    
=======
    public void setStatus(String status) {
        this.status = status;
    }
>>>>>>> 95f1158cc6ef49de8044e73c834fefc3261e60ec
}
