package com.senai.projeto_catraca.controller;


import com.senai.projeto_catraca.model.usuario.Coordenador;
import com.senai.projeto_catraca.model.dao.json.CoordenadorDAO;

import java.util.List;

public class CoordenadorController {
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();

    public String cadastrarCoordenador(String nome, String CPF, String senha, String endereco, String telefone){
        coordenadorDAO.inserir(new Coordenador(nome, CPF, senha, 0, endereco, telefone));
        return "Coordenador cadastrado com sucesso!";
    }
    public String atualizarCoordenador(int id, String nome, String CPF, String senha, String endereco, String telefone) {
        coordenadorDAO.atualizar(new Coordenador(nome, CPF, senha, id, endereco, telefone));
        return "Coordenador atualizado.";
    }
    public String removerCoordenador(int id){
        coordenadorDAO.remover(id);
        return "Coordenador removido.";
    }
    public List<Coordenador> listarCoordenadores(){
        return coordenadorDAO.listar();
    }
}