/*package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.curso.CursoDAO;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;

import java.util.ArrayList;
import java.util.List;

public class UCController {
    CursoDAO cursoDAO = new CursoDAO();

    public String cadastrarUC(String cargaHoraria, String nome) {
        cursoDAO.inserir(new UnidadeCurricular(0, cargaHoraria, nome));
        return "UC adicionada com sucesso!";
    }

    public String atualizarUC(int id,String cargaHoraria, String nome) {
        cursoDAO.atualizar(new UnidadeCurricular(id, cargaHoraria, nome));
        return "UC atualizada.";
    }

    public String removerUC(int id) {
        cursoDAO.remover(id);
        return "UC removida.";
    }

    public List<UnidadeCurricular> listarUC() {
        return cursoDAO.listarUC();
    }
}*/
