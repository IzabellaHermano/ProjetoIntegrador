package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.UCController;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;
import com.senai.projeto_catraca.model.usuario.ProfessorDAO;

import java.util.ArrayList;
import java.util.Scanner;

/*public class UnidadeCurricularView {

    public void addProfessor() {

        System.out.println("ID da UC que deseja adicionar um professor: ");
        int idUC = scanner.nextInt();
        scanner.nextLine();

        Optional<UnidadeCurricular> ucOptional = ucDAO.buscarPorId(idUC);

        if (ucOptional.isPresent()) {
            UnidadeCurricular uc = ucOptional.get();

            System.out.println("Digite o ID do professor que deseja adicionar a Unidade Curricular: ");
            System.out.println("--- Unidades Curriculares ---");
            for (Professor p : professorController.listarUC()) {
                System.out.printf("ID: %d | Nome: %s |\n", p.getId(), p.getNome());
            }
            int idprof = scanner.nextInt();
            scanner.nextLine();

            //código desenvolvido para pegar o professor pelo ID e colocar apenas o nome na lista de professores da UC.
            Optional<Professor> professorOptional = professorDAO.buscarPorId(idprof);
            if (professorOptional.isPresent()) {
                Professor p = professorOptional.get();
                uc.getListaProfessores().add(p.getNome());
                ucDAO.atualizar(uc);
                System.out.println("Professor adicionado à UC: " + uc.getNome());
            }
        } else System.out.println("Curso não encontrado. Caso tenha sido cadastrado recentemente, " +
                "reinicie o sistema para adicionar a Unidade Curricular.");

        }

    public void removerProfessor() {
        /* System.out.println("ID da Unidade Curricular que deseja remover um professor: ");
        int idUC = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ID do professor que deseja remover da Unidade Curricular: ");
        System.out.println("--- Professores ---");
        for (Professor p : controllerProf.listarProfessores()) {
            System.out.printf("ID: %d | Nome: %s |\n", p.getId(), p.getNome());
        }
        int idProf = scanner.nextInt();
        scanner.nextLine();

        Optional<Professor> profOptional = profDAO.buscarPorId(idProf);

        Professor p = profOptional.get();
        listaProfs.remove(p.getNome());
        System.out.println("Professor removido: " + p.getNome());

        controllerProf.addProf(listaProfs, idUC);

        */

