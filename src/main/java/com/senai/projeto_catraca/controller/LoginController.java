package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.dao.json.AQVDAO;
import com.senai.projeto_catraca.model.usuario.CoordenadorDAO;
import com.senai.projeto_catraca.model.usuario.ProfessorDao;
import com.senai.projeto_catraca.model.usuario.Usuario;
import com.senai.projeto_catraca.model.usuario.aluno.AlunoDAO;

import java.util.Optional;

public class LoginController {
    private final AlunoDAO alunoDAO= new AlunoDAO();
    private final AQVDAO aqvdao = new AQVDAO();
    private final ProfessorDao professorDao = new ProfessorDao();
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();

    public Optional <Usuario> autenticarUsuario (String nome, String senha){
        Optional <? extends  Usuario>aluno = alunoDAO.buscarPorLogin(nome);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) return  Optional.of(aluno.get());

        return Optional.empty();
    }

}

