package com.senai.projeto_catraca.model.usuario.aluno;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO {
    private List<Aluno> alunoList;
    private final String FILE_PATH = "ALUNOS.json";
    private final Gson gson = new Gson();

    public AlunoDAO() {
        alunoList = carregarAluno();

    }

    private List<Aluno> carregarAluno() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Aluno>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Aluno> listaAluno) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaAluno, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Aluno aluno) {
        int novoId = alunoList.stream().mapToInt(Aluno::getId).max().orElse(0) + 1;
        aluno.setId(novoId);
        alunoList.add(aluno);
        salvar(alunoList);
    }

    public void atualizar(Aluno aluno) {
        for (int i = 0; i < alunoList.size(); i++) {
            if (alunoList.get(i).getId() == aluno.getId()) {
                alunoList.set(i, aluno);
                break;
            }
        }
        salvar(alunoList);
    }

    public void removerAluno(int id) {
        alunoList.removeIf(a -> a.getId() == id);
        salvar(alunoList);
    }

    public Optional<Aluno> buscarPorLogin(String nome) {
        return alunoList.stream().filter(a -> a.getNome() == nome).findFirst();
    }

    public Optional<Aluno> buscarPorId(int id) {
        return alunoList.stream().filter(a -> a.getId() == id).findFirst();
    }

    public List<Aluno> listarAlunos() {
        return alunoList;

    }

    public Optional<Aluno> buscarPorRfid(String rfid) {
        return alunoList.stream().filter(a -> rfid.equals(a.getIdCartaoRfid())).findFirst();
    }

    public Optional<Integer> buscarRfid(String nome) {
        return alunoList.stream().filter(aluno -> aluno.getNome().equals(nome)).map(Aluno::getIdCartaoRfid).findFirst();
    }
}