package com.senai.projeto_catraca.controller;

import java.util.List;
import java.util.Optional;

import com.senai.projeto_catraca.model.dao.json.ProfessorDAO;
import com.senai.projeto_catraca.model.usuario.Professor;

public class ProfessorController {
    private final ProfessorDAO professorDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
    }

    public void cadastrarProfessor(String nome, String senha, String cpf, String endereco, String telefone, String disciplina) {
        Professor novoProfessor = new Professor(nome, senha, cpf, 0, endereco, telefone, disciplina);
        professorDAO.inserir(novoProfessor);
    }

    public List<Professor> listarTodosProfessores() {
        return professorDAO.listarTodos();
    }

    public boolean removerProfessor(int id) {
        Optional<Professor> professor = professorDAO.buscarPorId(id);
        if (professor.isPresent()) {
            professorDAO.remover(id);
            return true;
        }
        return false;
    }

    public Optional<Professor> buscarProfessorPorId(int id) {
        return professorDAO.buscarPorId(id);
    }

    public boolean atualizarProfessor(int id, String nome, String senha, String cpf, String endereco, String telefone, String disciplina) {
        Optional<Professor> professorOpt = professorDAO.buscarPorId(id);
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            professor.setNome(nome);
            professor.setSenha(senha);
            professor.setCPF(cpf);
            professor.setEndereco(endereco);
            professor.setTelefone(telefone);
            professor.setDisciplina(disciplina);
            professorDAO.atualizar(professor);
            return true;
        }
        return false;
    }

    public Optional<Professor> buscarProfessorPorLogin(String nome) {
        return professorDAO.buscarPorLogin(nome);
    }
}