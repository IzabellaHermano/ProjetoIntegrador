package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.usuario.AQV;
import com.senai.projeto_catraca.model.dao.json.AQVDAO;

import java.util.List;

public class AqvController {
    private final AQVDAO aqvdao = new AQVDAO();

    public String cadastrarAqv(String nome, String senha, String CPF, String endereco, String telefone){
        aqvdao.inserir(new AQV(nome, senha,CPF,0,endereco,telefone));
        return "[AQV CADASTRADO COM SUCESSO]";
    }
    public String atualizarAqv(String nome, String senha, String CPF,int id, String endereco, String telefone){
        aqvdao.atualizar(new AQV(nome, senha,CPF,id,endereco,telefone));
        return "[AQV ATUALIZADO COM SUCESSO]";
    }
    public String deletarAqv(int idD){
        aqvdao.removerAQV(idD);
        return "[AQV DELETADO COM SUCESSO]";
    }
    public List<AQV> listarAqv() {
        return aqvdao.listarAQV();
    }
}
