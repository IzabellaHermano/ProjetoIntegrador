package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.turma.horario.Ambiente;
import com.senai.projeto_catraca.model.dao.json.AmbienteDAO;

import java.util.List;

public class AmbienteController {
    private final AmbienteDAO ambienteDAO = new AmbienteDAO();

    public String cadastrarAmbiente(String Nome, int Numero) {
        ambienteDAO.inserir(new Ambiente(0, Nome, Numero));
        return "Ambiente cadastrado com sucesso.";
    }

    public String atualizarAmbiente(int id, String Nome, int Numero) {
        ambienteDAO.atualizar(new Ambiente(id, Nome, Numero));
        return "Ambiente atualizado com sucesso.";
    }
    public String removerAmbiente(int id) {
        ambienteDAO.remover(id);
        return "Ambiente atualizado com sucesso.";
    }
    public List<Ambiente> listarAmbiente(){
        return ambienteDAO.listarTodos();
    }
}
