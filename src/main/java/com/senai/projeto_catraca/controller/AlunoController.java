package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.usuario.aluno.Aluno;
import com.senai.projeto_catraca.model.usuario.aluno.AlunoDAO;

import java.util.List;

public class AlunoController {
    private final AlunoDAO alunoDAO = new AlunoDAO();

    public String cadastrarAluno(String nome, String senha, String CPF, String endereco, String telefone, String matricula,String idCartaoRfid){
       alunoDAO.inserir(new Aluno(nome, senha,CPF,0,endereco,telefone,idCartaoRfid,matricula));
       return "[ALUNO CADASTRADO COM SUCESSO]";
    }
    public String atualizarAluno(String nome, String senha, String CPF,int id, String endereco, String telefone, String matricula,String idCartaoRfid){
        alunoDAO.atualizar(new Aluno(nome, senha,CPF,id,endereco,telefone,idCartaoRfid,matricula));
        return "[ALUNO ATUALIZADO COM SUCESSO]";
    }
    public String deletarAluno(int idD){
        alunoDAO.removerAluno(idD);
        return "[ALUNO DELETADO COM SUCESSO]";
    }
    public List<Aluno> listarAlunos() {
        return alunoDAO.listarAlunos();
    }

}
