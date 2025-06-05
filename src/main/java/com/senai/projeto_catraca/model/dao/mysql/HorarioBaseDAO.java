package com.senai.projeto_catraca.model.dao.mysql;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.turma.horario.HorarioBase;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HorarioBaseDAO {
    private final String caminho = "horarios.json";
    private final Gson gson = new Gson();
    private final List<HorarioBase> horarioBase;

    public HorarioBaseDAO() {
        horarioBase = carregar();
    }


    private List<HorarioBase> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<HorarioBase>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    private void salvar(List<HorarioBase> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void inserir(HorarioBase horario) {
        int novoId = horarioBase.stream().mapToInt(HorarioBase::getId).max().orElse(0) + 1;
        horario.setId(novoId);
        horarioBase.add(horario);
        salvar(horarioBase);
    }
    public void atualizar(HorarioBase horario) {
        for (int i = 0; i < horarioBase.size(); i++) {
            if (horarioBase.get(i).getId() == horario.getId()) {
                horarioBase.set(i, horario);
                break;
            }
        }
        salvar(horarioBase);
    }
    public void remover(int id) {
        horarioBase.removeIf(h -> h.getId() == id);
        salvar(horarioBase);
    }
    public List<HorarioBase> listarTodos(){
          return horarioBase;
    }
}
