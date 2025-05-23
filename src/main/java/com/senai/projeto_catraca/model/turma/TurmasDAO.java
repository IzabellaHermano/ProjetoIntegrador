package com.senai.projeto_catraca.model.turma;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.turma.Turmas;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class TurmasDAO {

    private final String caminho = "turmas.json";
    private final Gson gson = new Gson();
    private final List<Turmas> turmas;

    public TurmasDAO() {
        turmas = carregar();
    }

    private List<Turmas> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Turmas>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Turmas> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Turmas turma) {
        int novoId = turmas.stream().mapToInt(Turmas::getId).max().orElse(0) + 1;
        turma.setId(novoId);
        turmas.add(turma);
        salvar(turmas);
    }

    public void atualizar(Turmas turma) {
        for (int i = 0; i < turmas.size(); i++) {
            if (turmas.get(i).getId() == turma.getId()) {
                turmas.set(i, turma);
                break;
            }
        }
        salvar(turmas);
    }

    public void remover(int id) {
        turmas.removeIf(t -> t.getId() == id);
        salvar(turmas);
    }

    public Optional<Turmas> buscarPorId(int id) {
        return turmas.stream().filter(t -> t.getId() == id).findFirst();
    }

    public List<Turmas> listarTodas() {
        return turmas;
    }
}
