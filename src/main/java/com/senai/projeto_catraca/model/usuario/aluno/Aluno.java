package com.senai.projeto_catraca.model.usuario.aluno;

import com.senai.projeto_catraca.model.usuario.Usuario;

import java.time.LocalTime;
import java.util.List;

public class Aluno extends Usuario {
    private String idCartaoRfid;
    private List <Justificativa> justificativas;
    private List <Ocorrencia> ocorrencias;
    private String matricula;

    public String getIdCartaoRfid() {
        return idCartaoRfid;
    }

    public void setIdCartaoRfid(String idCartaoRfid) {
        this.idCartaoRfid = idCartaoRfid;
    }

    public List<Justificativa> getJustificativas() {
        return justificativas;
    }

    public void setJustificativas(List<Justificativa> justificativas) {
        this.justificativas = justificativas;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Aluno(String nome, String senha, String CPF, int id, String endereco, String telefone, String idCartaoRfid, String matricula) {
        super(nome, senha, CPF, id, endereco, telefone);
        this.idCartaoRfid = idCartaoRfid;
        this.matricula = matricula;
    }

    public Aluno(String nome, String senha, String CPF, int id, String endereco, String telefone, String idCartaoRfid, List<Justificativa> justificativas, List<Ocorrencia> ocorrencias, String matricula) {
        super(nome, senha, CPF, id, endereco, telefone);
        this.idCartaoRfid = idCartaoRfid;
        this.justificativas = justificativas;
        this.ocorrencias = ocorrencias;
        this.matricula = matricula;
    }

    public boolean Atraso(LocalTime horarioEntrada) {
        return LocalTime.now().isAfter(horarioEntrada.plusMinutes(15));
    }
}
