package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class CursoDAO {
    private static ArrayList<Curso> listaCursos = new ArrayList<>();

    public static void adicionarCurso(Curso curso) {
        listaCursos.add(curso);
        salvarCurso();
    }

    private static void salvarCurso() {
        System.out.println("Curso salvo!");
    }

    public static ArrayList<Curso> listarCursos() {
        return listaCursos;
    }
    public static Curso buscarCursoPorId(int id) {
        for (Curso curso : listaCursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }
    public static boolean removerCurso(int id) {
        Curso curso = buscarCursoPorId(id);
        if (curso != null) {
            listaCursos.remove(curso);
            salvarCurso();
            return true;
        }
        return false;
    }

    public static void carregarCursos() {
        System.out.println("Cursos carregados!");
        listaCursos.add(new Curso("Python"));
        listaCursos.add(new Curso("Java"));
    }
}

