package com.senai.projeto_catraca.model.dao.json;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.senai.projeto_catraca.model.usuario.AQV;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class AQVDAO {
    private final String caminho = "aqvs.json";
    private final Gson gson = new Gson();
    private final List<AQV> aqvs;

    public AQVDAO() {
        aqvs = carregar();
    }

    private List<AQV> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<AQV>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<AQV> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(AQV aqv) {
        int novoId = aqvs.stream().mapToInt(AQV::getId).max().orElse(0) + 1;
        aqv.setId(novoId);
        aqvs.add(aqv);
        salvar(aqvs);
    }

    public void atualizar(AQV aqv) {
        for (int i = 0; i < aqvs.size(); i++) {
            if (aqvs.get(i).getId() == aqv.getId()) {
                aqvs.set(i, aqv);
                break;
            }
        }
        salvar(aqvs);
    }

    public void remover(int id) {
        aqvs.removeIf(a -> a.getId() == id);
        salvar(aqvs);
    }

    public Optional<AQV> buscarPorId(int id) {
        return carregar().stream().filter(a -> a.getId() == id).findFirst();
    }

    public Optional<AQV> buscarPorLogin(String login) {
        return aqvs.stream().filter(a -> a.getNome().equals(login)).findFirst();
    }

    public List<AQV> listarTodos() {
        return aqvs;
    }
}
