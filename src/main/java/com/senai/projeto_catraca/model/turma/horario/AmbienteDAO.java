package com.senai.projeto_catraca.model.turma.horario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AmbienteDAO {
    private final String caminho = "ambiente.json";
    private final Gson gson = new Gson();
    private final List<Ambiente> ambientes;

    public AmbienteDAO() {
        ambientes = carregar();
    }

    private List<Ambiente> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Ambiente>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Ambiente> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Ambiente ambiente) {

        int novoId = ambientes.stream().mapToInt(Ambiente::getId).max().orElse(0) + 1;
        ambiente.setId(novoId);
        ambientes.add(ambiente);
        salvar(ambientes);
    }

    public void atualizar(Ambiente ambiente) {
        for (int i = 0; i < ambientes.size(); i++) {
            if (ambientes.get(i).getId() == ambiente.getId()) {
                ambientes.set(i, ambiente);
                break;
            }
        }
        salvar(ambientes);
    }

    public void remover(int id) {
        ambientes.removeIf(p -> p.getId() == id);
        salvar(ambientes);
    }
    public List<Ambiente> listarTodos() {
        return ambientes;
    }
}
