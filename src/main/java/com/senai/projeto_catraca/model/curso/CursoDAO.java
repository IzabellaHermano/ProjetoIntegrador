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


public class CursoDAO {
    private final String caminho = "curso.json";
    private final Gson gson = new Gson();
    private final List<Curso> cursos;

    public CursoDAO() {
        this.cursos = carregar();
    }

    public List<Curso> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Curso>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    private void salvar(List<Curso> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void inserir(Curso curso) {
        int novoId = cursos.stream().mapToInt(Curso::getId).max().orElse(0) + 1;
        curso.setId(novoId);
        cursos.add(curso);
        salvar(cursos);
    }

    public void atualizar(Curso curso) {
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getId() == curso.getId()) {
                cursos.set(i, curso);
                break;
            }
        }
        salvar(cursos);
    }

    public void remover(int id) {
        cursos.removeIf(a -> a.getId() == id);
        salvar(cursos);
    }

    public Optional<Curso> buscarPorId(int idCurso) {
        return cursos.stream().filter(c -> c.getId() == idCurso).findFirst();
    }

    public List<Curso> listar() {
        return cursos;
    }

    //parte para UCS
    public void inserirUC(int idCurso, UnidadeCurricular unidadeCurricular) {
        Curso curso = cursos.stream().filter(c -> c.getId() == idCurso).findFirst().orElse(null);
        if (curso != null) {
            int novoIdUC = curso.getListaUnidadeCurricular().stream().mapToInt(UnidadeCurricular::getId).max().orElse(0) + 1;
            unidadeCurricular.setId(novoIdUC);
            curso.getListaUnidadeCurricular().add(unidadeCurricular);
            salvar(cursos);
        }
    }

    public void removerUC(int idCurso, int idUC) {
        Optional<Curso> cursoOpt = buscarPorId(idCurso);
        if (cursoOpt.isPresent()) {
            Curso c = cursoOpt.get();
            c.getListaUnidadeCurricular().removeIf(uc -> uc.getId() == idUC);
            salvar(cursos);
        }
    }

}