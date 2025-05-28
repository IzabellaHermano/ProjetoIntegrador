package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.UCController;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;

import java.util.ArrayList;
import java.util.Scanner;

public class UnidadeCurricularView {
    private final UCController controller = new UCController();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UnidadeCurricularView unidadeCurricularView = new UnidadeCurricularView();
        unidadeCurricularView.menu();
    }

    public void menu() {
        System.out.println("--Unidades Curriculares--");
        String menu = """
                 _____________________________________
                 |1. Cadastrar Unidade Curricular    |
                 |2. Deletar Unidade Curricular      |
                 |3. Atualizar Unidade Curricular    |
                 |4. Exibir Unidade Curricular       |
                 |5. Sair                            |
                 |___________________________________|
                """;

        int opcao;
        do {
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    deletar();
                    break;
                case 3:
                    atualizar();
                    break;
                case 4:
                    exibir();
                    break;
                case 5:
                    addProfessor();
                    break;
                case 6:
                    removerProfessor();
                    break;
                case 7:
                    System.out.println("Encerrando o Sistema!...");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
            }
        } while (opcao != 5);

    }

    public void cadastrar() {
        System.out.println("Digite as seguintes informações: ");
        ArrayList<String> listaProfessores = null;
        System.out.print("Carga horaria (HH:mm): ");
        String cargaHoraria = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        controller.cadastrarUC(listaProfessores, cargaHoraria, nome);
    }

    public void deletar() {
        System.out.println("--- Unidades Curriculares ---");
        for (UnidadeCurricular c : controller.listarUC()) {
            System.out.printf("ID: %d | Nome: %s\n", c.getId(), c.getNome());
        }
        System.out.print("Digite o id do coordenador que deseja deletar: ");
        int id = scanner.nextInt();
        controller.removerUC(id);
    }

    public void atualizar() {
        System.out.print("Digite o ID da matéria que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite as seguintes informações: ");

        ArrayList<String> listaProfessores = null;
        System.out.print("Carga horaria (HH:mm): ");
        String cargaHoraria = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        controller.atualizarUC(id, listaProfessores, cargaHoraria, nome);
    }

    public void exibir() {
        if (controller.listarUC().isEmpty()) {
            System.out.println("Não há Unidades Curriculares cadastradas!!");
        } else System.out.println("--- Unidades Curriculares ---");
        for (UnidadeCurricular uc : controller.listarUC()) {
            System.out.printf("ID: %d | Nome: %s | Carga Horaria: %s | Lista dos professores: %s\n",
                    uc.getId(), uc.getNome(), uc.getCargaHoraria(), uc.getListaProfessores());
        }
    }

    public void addProfessor() {

        /* System.out.println("ID da Unidade Curricular que deseja adicionar o professor: ");
        int idUC = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ID do professor que deseja adicionar a Unidade Curricular: ");
        System.out.println("--- Professores ---");
        for (Professor p : controllerProf.listarProfessores()) {
            System.out.printf("ID: %d | Nome: %s |\n", p.getId(), p.getNome());
        }
        int idProf = scanner.nextInt();
        scanner.nextLine();

        //código desenvolvido para pegar o professor pelo ID e colocar apenas o nome na lista de professores da Unidade Curricular.
        Optional<Professor> ucOptional = profDAO.buscarPorId(idProf);
        Professor p = ucOptional.get();
        listaProfs.add(p.getNome());
        System.out.println("Unidade Curricular adicionada: " + p.getNome());


        controllerProf.addProf(listaProfs, idUC);
        */

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
    }
}
