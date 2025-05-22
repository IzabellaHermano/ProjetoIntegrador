package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class CursoDAO {
    private ArrayList<Curso> listaCursos = new ArrayList<>();

    // Adicionar Curso novo //
    public void adicionarCurso(Curso curso) {
        listaCursos.add(curso);
    }

    // Retornar todos os cursos //
    public ArrayList<Curso> listarCursos() {
        return listaCursos;
    }
    // Metodo buscar curso por id //
    public Curso buscarCursoPorId(int id) {
        for (Curso curso : listaCursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        // Retorna null se não encontrar //
        return null;
    }
    // Remover curso por id //
    public boolean removerCurso(int id) {
        Curso curso = buscarCursoPorId(id);
        if (curso != null) {
            listaCursos.remove(curso);
            return true;
        }
        return false;
    }
}





