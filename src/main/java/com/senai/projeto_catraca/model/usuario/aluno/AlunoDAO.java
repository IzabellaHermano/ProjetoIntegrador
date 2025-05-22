package com.senai.projeto_catraca.model.usuario.aluno;

import com.google.gson.Gson;

import java.util.List;

public class AlunoDAO {
    private List<Aluno> alunoList;
    private final  String FILE_PATH = "ALUNOS.json";
    private final Gson gson = new Gson();

}
