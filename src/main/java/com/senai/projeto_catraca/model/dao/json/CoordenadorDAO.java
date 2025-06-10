package com.senai.projeto_catraca.model.dao.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.usuario.AQV;
import com.senai.projeto_catraca.model.usuario.Coordenador;
import com.senai.projeto_catraca.model.usuario.Professor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CoordenadorDAO {
    private final String caminho = "coordenadores.json";
    private final Gson gson = new Gson();
    private final List<Coordenador> coordenadores;

    public CoordenadorDAO() {
        coordenadores = carregar();
    }


    private List<Coordenador> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Coordenador>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    private void salvar(List<Coordenador> lista){
        try (FileWriter writer = new FileWriter(caminho)){
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void inserir(Coordenador coordenador) {
        int novoId = coordenadores.stream().mapToInt(Coordenador::getId).max().orElse(0) + 1;
        coordenador.setId(novoId);
        coordenadores.add(coordenador);
        salvar(coordenadores);
    }
    public void atualizar(Coordenador coordenador){
        for (int i = 0; i < coordenadores.size(); i++) {
            if (coordenadores.get(i).getId() == coordenador.getId()){
                coordenadores.set(i, coordenador);
                break;
            }
        }
        salvar(coordenadores);
    }
    public void remover(int id) {
        coordenadores.removeIf(a -> a.getId() == id);
        salvar(coordenadores);
    }
    public Optional<Coordenador> buscarPorLogin(String nome) {
        return coordenadores.stream().filter(c -> Objects.equals(c.getNome(), nome)).findFirst();
    }

    public Optional<Coordenador> buscarPorId(int id) {
        return coordenadores.stream().filter(c -> c.getId() == id).findFirst();
    }
    public List<Coordenador> listar(){
        return coordenadores;
    }
}