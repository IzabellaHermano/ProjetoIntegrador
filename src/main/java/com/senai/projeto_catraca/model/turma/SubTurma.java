package com.senai.projeto_catraca.model.turma;

import com.senai.projeto_catraca.model.curso.Semestre;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public class SubTurma {
    private int id;
    private List<Aluno> alunos;

    public SubTurma(int id, List<Aluno> alunos) {
        this.id = id;
        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Usado para montar o texto final com performance melhor

//        sb.append("SubTurma com ").append(alunos.size()).append(" alunos:\n");

        for (Aluno a : alunos) {
            sb.append("  Nome: ").append(a.getNome())
                    .append(" | Matrícula: ").append(a.getMatricula())
                    .append(" | CPF: ").append(a.getCPF())
                    .append(" | RFID: ").append(a.getIdCartaoRfid())
                    .append(" | Endereço: ").append(a.getEndereco())
                    .append(" | Telefone: ").append(a.getTelefone())
                    .append("\n"); // Nova linha para cada aluno
        }

        return sb.toString(); // Retorna o texto formatado
    }

}


