package com.senai.projeto_catraca.model.usuario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

}
