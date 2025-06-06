package com.senai.projeto_catraca.model.usuario.aluno;

import com.senai.projeto_catraca.model.usuario.Usuario;

import java.time.LocalTime;
import java.util.List;

public class Aluno extends Usuario {
    private String idCartaoRfid;
    private List <Justificativa> justificativas;
    private List <Ocorrencia> ocorrencias;
    private int matricula;

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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Aluno(String nome, String senha, String CPF, int id, String endereco, String telefone, String idCartaoRfid, List<Justificativa> justificativas, List<Ocorrencia> ocorrencias, int matricula) {
        super(nome, senha, CPF, id, endereco, telefone);
        this.idCartaoRfid = idCartaoRfid;
        this.justificativas = justificativas;
        this.ocorrencias = ocorrencias;
        this.matricula = matricula;
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

  public String getNome(){
        return getNome();
  }
}
