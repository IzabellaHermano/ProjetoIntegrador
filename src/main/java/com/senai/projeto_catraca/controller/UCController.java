package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.curso.UnidadeCurricular;
import com.senai.projeto_catraca.model.curso.UnidadeCurricularDAO;

import java.util.ArrayList;
import java.util.List;

public class UCController {
    UnidadeCurricularDAO unidadeCurricularDAO = new UnidadeCurricularDAO();

    public String cadastrarUC(ArrayList<String> listaProfessores, String cargaHoraria, String nome) {
        unidadeCurricularDAO.inserir(new UnidadeCurricular(0,listaProfessores, cargaHoraria, nome));
        return "UC adicionada com sucesso!";
    }

    public String atualizarUC(int id, ArrayList<String> listaProfessores, String cargaHoraria, String nome) {
        unidadeCurricularDAO.atualizar(new UnidadeCurricular(id, listaProfessores, cargaHoraria, nome));
        return "UC atualizada.";
    }

    public String removerUC(int id) {
        unidadeCurricularDAO.remover(id);
        return "UC removida.";
    }

    public List<UnidadeCurricular> listarUC() {
        return unidadeCurricularDAO.listar();
    }
}
