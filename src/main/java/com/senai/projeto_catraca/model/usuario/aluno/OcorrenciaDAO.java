package com.senai.projeto_catraca.model.usuario.aluno;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.usuario.aluno.Ocorrencia;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaDAO {
    private static final String FILE_PATH = "ocorrencias.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type listType = new TypeToken<List<Ocorrencia>>() {}.getType();

    private List<Ocorrencia> lerOcorrencias() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            List<Ocorrencia> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvarOcorrencias(List<Ocorrencia> lista) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Ocorrencia ocorrencia) {
        List<Ocorrencia> lista = lerOcorrencias();
        lista.add(ocorrencia);
        salvarOcorrencias(lista);
    }

    public boolean atualizar(Ocorrencia ocorrenciaAtualizada) {
        List<Ocorrencia> lista = lerOcorrencias();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == ocorrenciaAtualizada.getId()) {
                lista.set(i, ocorrenciaAtualizada);
                salvarOcorrencias(lista);
                return true;
            }
        }
        return false;
    }

    public boolean deletar(int id) {
        List<Ocorrencia> lista = lerOcorrencias();
        boolean removido = lista.removeIf(o -> o.getId() == id);
        if (removido) {
            salvarOcorrencias(lista);
        }
        return removido;
    }

    public List<Ocorrencia> listar() {
        return lerOcorrencias();
    }

    public Ocorrencia buscarPorId(int id) {
        return lerOcorrencias().stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public void setStatusOcorrencia(Ocorrencia o, String status){
        o.setStatus(status);
    }
}