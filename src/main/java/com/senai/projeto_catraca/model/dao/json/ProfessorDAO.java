package com.senai.projeto_catraca.model.dao.json;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.usuario.Professor;

public class ProfessorDAO {
    private final String caminho = "professores.json";
    private final Gson gson = new Gson();
    private final List<Professor> professores;

    public ProfessorDAO(){
        professores = carregar();
    }

    private List<Professor> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Professor>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Professor> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Professor professor) {

        int novoId = professores.stream().mapToInt(Professor::getId).max().orElse(0) + 1;
        professor.setId(novoId);
        professores.add(professor);
        salvar(professores);
    }

    public void atualizar(Professor professor) {
        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getId() == professor.getId()) {
                professores.set(i, professor);
                break;
            }
        }
        salvar(professores);
    }

    public void remover(int id) {
        professores.removeIf(p -> p.getId() == id);
        salvar(professores);
    }

    public Optional<Professor> buscarPorId(int id) {
        return carregar().stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Professor> buscarPorLogin(String nome) {
        return professores.stream().filter(p -> Objects.equals(p.getNome(), nome)).findFirst();
    }

    public List<Professor> listarTodos() {
        return professores;
    }
}

