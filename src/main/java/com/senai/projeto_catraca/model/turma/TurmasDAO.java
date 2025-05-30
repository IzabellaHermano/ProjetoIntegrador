package com.senai.projeto_catraca.model.turma;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.turma.horario.Ambiente;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TurmasDAO {
 private final String caminho = "turma.json";
 private final Gson gson = new Gson();
 private final List<Turmas> turma;

    public TurmasDAO() {
        this.turma = carregar();
    }
    private List<Turmas> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Turmas>>() {}.getType();
            List<Turmas> lista = gson.fromJson(reader, listType);
            return (lista != null) ? lista : new ArrayList<>();
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
    public void inserir(Turmas turmas) {
        int novoId = turma.stream().mapToInt(Turmas::getId).max().orElse(0) + 1;
        turmas.setId(novoId);
        turma.add(turmas);
        salvar(turma);
    }

    public void atualizar(Turmas turmas) {
        for (int i = 0; i < turma.size(); i++) {
            if (turma.get(i).getId() == turmas.getId()) {
                turma.set(i, turmas);
                break;
            }
        }
        salvar(turma);
    }
    public void remover(int id) {
        turma.removeIf(p -> p.getId() == id);
        salvar(turma);
    }
    public List<Turmas> listarTodos() {
        return turma;
    }
}
