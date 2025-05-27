package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.curso.Curso;
import com.senai.projeto_catraca.model.curso.CursoDAO;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;
import com.senai.projeto_catraca.model.curso.UnidadeCurricularDAO;
import java.util.List;


public class CursoController {
        public static void adicionarCurso(String nome) {
            Curso novoCurso = new Curso(nome);
            CursoDAO.adicionarCurso(novoCurso);
            System.out.println("Curso adicionado com sucesso.");

        }
        public static boolean removerCurso(int id) {
            return CursoDAO.removerCurso(id);
        }

        public static void listarCursos() {
            List<Curso> cursos = CursoDAO.listarCursos();
            if (cursos.isEmpty()) {
                System.out.println("Nenhum curso cadastrado.");
            } else {
                for (Curso curso : cursos) {
                    System.out.println("- " + curso.getId() + " | " + curso.getNome());
                }
            }
        }

        public static void adicionarUnidadeCurricular(String nomeUC, int idCurso) {
            Curso curso = CursoDAO.buscarCursoPorId(idCurso);
            if (curso == null) {
                System.out.println("Curso não encontrado.");
                return;
            }
            UnidadeCurricular novaUC = new UnidadeCurricular(nomeUC, idCurso);
            UnidadeCurricularDAO.adicionarUC(novaUC);
            System.out.println("Unidade Curricular adicionada com sucesso ao curso: " + curso.getNome());
        }

        public static void listarUnidadesCurriculares() {
            List<UnidadeCurricular> ucs = UnidadeCurricularDAO.listarUCs();
            if (ucs.isEmpty()) {
                System.out.println("Nenhuma unidade curricular cadastrada.");
            } else {
                for (UnidadeCurricular uc : ucs) {
                    System.out.println("- " + uc.getNome() + " (Curso ID: " + uc.getIdCurso() + ")");
                }
            }
        }

        public static boolean removerUnidadeCurricular(String nomeUC) {
            List<UnidadeCurricular> ucs = UnidadeCurricularDAO.listarUCs();
            for (UnidadeCurricular uc : ucs) {
                if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                    UnidadeCurricularDAO.removerUC(uc.getId());
                    return true;
                }
            }
            return false;
        }
    }
