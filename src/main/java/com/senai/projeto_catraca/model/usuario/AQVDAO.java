package com.senai.projeto_catraca.model.usuario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AQVDAO {
    private List<AQV> aqvList;
    private final  String FILE_PATH = "AQV.json";
    private final Gson gson = new Gson();

    public AQVDAO (){
        aqvList = carregarAQV();

    }

    private List<AQV> carregarAQV() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<AQV>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    private void salvarAQV(List<AQV> aqvList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(aqvList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void inserir(AQV aqv) {
        int novoId = aqvList.stream().mapToInt(AQV::getId).max().orElse(0) + 1;
        aqv.setId(novoId);
        aqvList.add(aqv);
        salvarAQV(aqvList);
    }
    public void atualizar(AQV aqv) {
        for (int i = 0; i < aqvList.size(); i++) {
            if (aqvList.get(i).getId() == aqv.getId()) {
                aqvList.set(i, aqv);
                break;
            }
        }
        salvarAQV(aqvList);
    }
    public void removerAQV(int id) {
        aqvList.removeIf(a -> a.getId() == id);
        salvarAQV(aqvList);
    }
    public Optional<AQV> buscarPorId(int id) {
        return aqvList.stream().filter(a -> a.getId() == id).findFirst();
    }
    public List<AQV>listarAQV(){
        return aqvList;

    }

}
