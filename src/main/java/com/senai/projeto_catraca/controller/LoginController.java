package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.dao.json.AQVDAO;
import com.senai.projeto_catraca.model.dao.json.CoordenadorDAO;
import com.senai.projeto_catraca.model.dao.json.ProfessorDAO;

import com.senai.projeto_catraca.model.usuario.Usuario;
import com.senai.projeto_catraca.model.usuario.aluno.AlunoDAO;

import java.util.Optional;

public class LoginController {
    private final AlunoDAO alunoDAO= new AlunoDAO();
    private final AQVDAO aqvdao = new AQVDAO();
    private final ProfessorDAO professorDao = new ProfessorDAO();
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();

    public Optional <Usuario> autenticarUsuario (String nome, String senha){
        Optional <? extends  Usuario>aluno = alunoDAO.buscarPorLogin(nome);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) return  Optional.of(aluno.get());

        Optional <?extends  Usuario > aqv = aqvdao.buscarPorLogin(nome);
        if (aqv.isPresent() && aqv.get().getSenha().equals(senha)) return  Optional.of(aqv.get());

        Optional <? extends  Usuario > professor = professorDao.buscarPorLogin(nome);
        if (professor.isPresent() && professor.get().getSenha().equals(senha) ) return  Optional.of(professor.get());

        Optional <? extends Usuario > coordenador = coordenadorDAO.buscarPorLogin(nome);
        if (coordenador.isPresent() && coordenador.get().getSenha().equals(senha)) return  Optional.of(coordenador.get());

        return Optional.empty();
    }

}

