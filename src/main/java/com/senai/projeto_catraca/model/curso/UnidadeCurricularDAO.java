package com.senai.projeto_catraca.model.curso;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnidadeCurricularDAO {
    private final String caminho = "unidadesCurriculares.json";
    private final Gson gson = new Gson();
    private final List<UnidadeCurricular> unidadeCurriculares;

    public UnidadeCurricularDAO() {
        this.unidadeCurriculares = carregar();
    }

    private List<UnidadeCurricular> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<UnidadeCurricular>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<UnidadeCurricular> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(UnidadeCurricular unidadeCurricular) {
        int novoId = unidadeCurriculares.stream().mapToInt(UnidadeCurricular::getId).max().orElse(0) + 1;
        unidadeCurricular.setId(novoId);
        unidadeCurriculares.add(unidadeCurricular);
        salvar(unidadeCurriculares);
    }

    public void atualizar(UnidadeCurricular unidadeCurricular) {
        for (int i = 0; i < unidadeCurriculares.size(); i++) {
            if (unidadeCurriculares.get(i).getId() == unidadeCurricular.getId()) {
                unidadeCurriculares.set(i, unidadeCurricular);
                break;
            }
        }
        salvar(unidadeCurriculares);
    }

    public void remover(int id) {
        unidadeCurriculares.removeIf(a -> a.getId() == id);
        salvar(unidadeCurriculares);
    }

    public List<UnidadeCurricular> listar() {
        return unidadeCurriculares;
    }

    public Optional<UnidadeCurricular> buscarPorId(int id) {
        return carregar().stream().filter(uc -> uc.getId() == id).findFirst();
    }
}