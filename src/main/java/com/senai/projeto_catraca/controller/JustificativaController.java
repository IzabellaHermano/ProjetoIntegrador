package com.senai.projeto_catraca.controller;


import com.senai.projeto_catraca.model.usuario.aluno.Justificativa;
import com.senai.projeto_catraca.model.usuario.aluno.JustificativaDAO;

import java.util.List;
import java.util.Optional;

public class JustificativaController {
    private final JustificativaDAO justificativaDAO = new JustificativaDAO();

    public String cadastrarJustificativa(String tipo, String descricao, String data, String anexo) {
        if (anexo.isEmpty()) {
            anexo = "Sem anexo";
        }
        Justificativa justificativa = new Justificativa(tipo, anexo, data, descricao, 0, "Aguardando aprovação da AQV...");
        justificativaDAO.inserir(justificativa);
        return "Justificativa criada com sucesso!";
    }

    public String atualizarJustificativa(String tipo, String descricao, String data, int id, String anexo) {
        justificativaDAO.atualizar(new Justificativa(tipo, anexo, data, descricao, id,"Aguardando aprovação da AQV..."));
        return "Justificativa atualizada.";
    }

    public String removerJustificativa(int id) {
        justificativaDAO.remover(id);
        return "Justificativa removida.";
    }

    public List<Justificativa> listarJustificativa() {
        return justificativaDAO.listar();
    }

    public Optional<Justificativa> buscarPorId(int idJust){
        return justificativaDAO.buscarPorId(idJust);
    }
    public void setStatus(String tipo, String descricao, String data, int id, String anexo, String status){
        justificativaDAO.setStatus(new Justificativa(tipo, anexo, data, descricao, id, status));
    }

}