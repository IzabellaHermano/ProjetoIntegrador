package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.curso.Curso;
import com.senai.projeto_catraca.model.curso.CursoDAO;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;
import com.senai.projeto_catraca.model.curso.UnidadeCurricularDAO;

import java.util.ArrayList;
import java.util.List;


public class CursoController {
    CursoDAO cursoDAO = new CursoDAO();

    public String cadastrarCurso(String nome, String tipo, int duracao, ArrayList<String> listaUCs) {
        cursoDAO.inserir(new Curso(nome, tipo, duracao, 0, listaUCs));
        return "Curso adicionado com sucesso!";
    }

    public String atualizarCurso(String nome, String tipo, int duracao, int id,ArrayList<String> listaUCs) {
        cursoDAO.atualizar(new Curso(nome, tipo, duracao, id, listaUCs));
        return "Curso atualizado.";
    }

    public String removerCurso(int id) {
        cursoDAO.remover(id);
        return "Curso removido.";
    }

    public List<Curso> listarCurso() {
        return cursoDAO.listar();
    }

    public String addUC(ArrayList<String> listaUCs, int id){
        cursoDAO.atualizarUC(id, listaUCs);
        return "UC adicionada com sucesso!";
    }
}