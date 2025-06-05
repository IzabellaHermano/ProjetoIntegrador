package com.senai.projeto_catraca.model.usuario.aluno;

import com.senai.projeto_catraca.model.usuario.Usuario;

import java.time.LocalTime;

public class Aluno extends Usuario {
    private int idCartaoRfid;
    private int matricula;

    public int getIdCartaoRfid() {
        return idCartaoRfid;
    }

    public void setIdCartaoRfid(int idCartaoRfid) {
        this.idCartaoRfid = idCartaoRfid;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Aluno(String nome, String senha, String CPF, int id, String endereco, String telefone, int idCartaoRfid, int matricula) {
        super(nome, senha, CPF, id, endereco, telefone);
        this.idCartaoRfid = idCartaoRfid;
        this.matricula = matricula;
    }
    public boolean Atraso(LocalTime horarioEntrada) {
        return LocalTime.now().isAfter(horarioEntrada.plusMinutes(15));
    }
}
