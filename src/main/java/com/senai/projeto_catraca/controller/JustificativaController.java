package com.senai.projeto_catraca.controller;


import com.senai.projeto_catraca.model.usuario.aluno.Justificativa;
import com.senai.projeto_catraca.model.usuario.aluno.JustificativaDAO;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class JustificativaController {
    private final JustificativaDAO justificativaDAO = new JustificativaDAO();

    public String cadastrarJustificativa(String tipo, String descricao, String data, String anexo){
        justificativaDAO.inserir(new Justificativa(tipo, anexo, data, descricao, 0));
        return "Justificativa criada com sucesso!";
    }
    public String atualizarJustificativa(String tipo, String descricao, String data, String anexo, int id) {
        justificativaDAO.atualizar(new Justificativa(tipo, anexo, data, descricao, id));
        return "Justificativa atualizada.";
    }
    public String removerJustificativa(int id){
        justificativaDAO.remover(id);
        return "Justificativa removida.";
    }
    public List<Justificativa> listarJustificativa(){
        return justificativaDAO.listar();
    }
}

