package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.curso.Curso;
import com.senai.projeto_catraca.model.curso.CursoDAO;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CursoController {
    CursoDAO cursoDAO = new CursoDAO();


    public String cadastrarCurso(String nome, String tipo, int duracao, ArrayList<UnidadeCurricular> listaUCs) {
        cursoDAO.inserir(new Curso(nome, tipo, duracao, 0, listaUCs));
        return "Curso adicionado com sucesso!";
    }

    public String atualizarCurso(String nome, String tipo, int duracao, int id,ArrayList<UnidadeCurricular> listaUCs) {
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

    public Optional<Curso> buscaPorId(int idCurso){
        return cursoDAO.buscarPorId(idCurso);
    }

    public UnidadeCurricular cadastrarUC(int idCurso, String cargaHoraria, String nome) {
        UnidadeCurricular unidadecurricular  = new UnidadeCurricular(0, cargaHoraria, nome);
        cursoDAO.inserirUC(idCurso, unidadecurricular);
        return unidadecurricular;
    }

    public String removerUC(int idCurso, int idUC) {
        cursoDAO.removerUC(idCurso, idUC);
        return "UC removida.";
    }


}