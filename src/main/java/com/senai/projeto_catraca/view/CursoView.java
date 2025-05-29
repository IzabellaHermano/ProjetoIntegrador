package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.CursoController;
import com.senai.projeto_catraca.controller.UCController;
import com.senai.projeto_catraca.model.curso.Curso;
import com.senai.projeto_catraca.model.curso.CursoDAO;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CursoView {
    private final ArrayList<UnidadeCurricular> listaUCs = new ArrayList<>();
    private final CursoController controllerCurso = new CursoController();
    private final UCController controllerUC = new UCController();
    private final Scanner scanner = new Scanner(System.in);;
    private final CursoDAO cursoDAO = new CursoDAO();

    public static void main(String[] args) {
        CursoView cursoView = new CursoView();
        cursoView.menu();
    }

    public void menu() {
        String menu = """
                 _______________________________________________
                 |      1. Adicionar cursos                    |
                 |      2. Atualizar cursos                    |
                 |      3. Remover cursos                      |
                 |      4. Listar cursos                       |
                 |      5. Adicionar unidades curiculares      |
                 |      7. Sair                                |
                 |_____________________________________________|
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
                    atualizar();
                    break;
                case 3:
                    deletar();
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    //adicionarUC();
                    break;
                case 6:
                   // removerUC();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
        scanner.close();
    }

    public void cadastrar() {
        System.out.println("Digite as seguintes informações:");
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Duração do curso (anos): ");
        int duracao = scanner.nextInt();

        // parte dedicada ao tipo do curso
        String tipo;
        System.out.print("""
                Selecione do curso:
                1. Técnico
                2. KAF
                """);
        int resposta = scanner.nextInt();
        scanner.nextLine();
        if (resposta == 1) {
            tipo = "Técnico";
        } else tipo = "KAF";


        controllerCurso.cadastrarCurso(nome, tipo, duracao, listaUCs);
        System.out.println("Curso cadastrado com sucesso!");
    }

    public void atualizar() {
        System.out.print("Digite o ID do Curso que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite as seguintes informações:");
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Duração do curso (anos): ");
        int duracao = scanner.nextInt();

        // parte dedicada ao tipo do curso
        String tipo;
        System.out.print("""
                Selecione do curso:
                1. Técnico
                2. KAF
                """);
        int resposta = scanner.nextInt();
        scanner.nextLine();
        if (resposta == 1) {
            tipo = "Técnico";
        } else tipo = "KAF";

        controllerCurso.atualizarCurso(nome, tipo, duracao, id, listaUCs);
        System.out.println("Curso atualizado com sucesso!");
    }

    public void deletar() {
        System.out.println("--- Cursos ---");
        for (Curso cs : controllerCurso.listarCurso()) {
            System.out.printf("ID: %d | Nome: %s",
                    cs.getId(), cs.getNome());
        }
        System.out.print("Digite o id do curso que deseja deletar: ");
        int id = scanner.nextInt();
        controllerCurso.removerCurso(id);
    }

    public void listar() {
        System.out.println("--- Cursos ---");
        for (Curso cs : controllerCurso.listarCurso()) {
            System.out.printf("ID: %d | Nome: %s | Duração: %s | Tipo do curso: %s| Unidades Curriculares: %s\n",
                    cs.getId(), cs.getNome(), cs.getDuracao(), cs.getTipo(), cs.getUnidadeCurricular());
        }
    }

    public void menuUC() {
        System.out.println("--Unidades Curriculares--");
        String menu = """
                 _____________________________________
                 |1. Cadastrar Unidade Curricular    |
                 |2. Deletar Unidade Curricular      |
                 |3. Atualizar Unidade Curricular    |
                 |4. Exibir Unidade Curricular       |
                 |5. VOLTAR                            |
                 |___________________________________|
                """;

        int opcao;
        do {
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarUC();
                    break;
                case 2:
                    deletarUC();
                    break;
                case 3:
                    atualizarUC();
                    break;
                case 4:
                    exibirUC();
                    break;
                case 5:
                    menu();
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
            }
        } while (opcao != 5);

    }

    public void cadastrarUC() {
        System.out.println("Digite as seguintes informações: ");
        System.out.print("Carga horaria (HH:mm): ");
        String cargaHoraria = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        controllerUC.cadastrarUC(cargaHoraria, nome);
    }

    public void deletarUC() {
        System.out.println("--- Unidades Curriculares ---");
        for (UnidadeCurricular c : controllerUC.listarUC()) {
            System.out.printf("ID: %d | Nome: %s\n", c.getId(), c.getNome());
        }
        System.out.print("Digite o id do coordenador que deseja deletar: ");
        int id = scanner.nextInt();
        controllerUC.removerUC(id);
    }

    public void atualizarUC() {
        System.out.print("Digite o ID da matéria que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite as seguintes informações: ");


        System.out.print("Carga horaria (HH:mm): ");
        String cargaHoraria = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        controllerUC.atualizarUC(id, cargaHoraria, nome);
    }

    public void exibirUC() {
        if (controllerUC.listarUC().isEmpty()) {
            System.out.println("Não há Unidades Curriculares cadastradas!!");
        } else System.out.println("--- Unidades Curriculares ---");
        for (UnidadeCurricular uc : controllerUC.listarUC()) {
            System.out.printf("ID: %d | Nome: %s | Carga Horaria: %s | Lista dos professores: (depois)\n",
                    uc.getId(), uc.getNome(), uc.getCargaHoraria());
        }
    }

   /* public void adicionarUC() {
        System.out.print("ID do curso que deseja adicionar uma UC: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine();

        Optional<Curso> cursoOptional = cursoDAO.buscarPorId(idCurso);

        if (cursoOptional.isPresent()) {
            Curso c = cursoOptional.get();

            System.out.println("Digite o ID da UC que deseja adicionar ao curso: ");
            System.out.println("--- Unidades Curriculares ---");
            for (UnidadeCurricular uc : controllerUC.listarUC()) {
                System.out.printf("ID: %d | Nome: %s |\n", uc.getId(), uc.getNome());
            }
            int idUC = scanner.nextInt();
            scanner.nextLine();

            //código desenvolvido para pegar a matéria pelo ID e colocar apenas o nome na lista de UCs do curso.
            Optional<UnidadeCurricular> ucOptional = ucDAO.buscarPorId(idUC);
            if (ucOptional.isPresent()) {
                UnidadeCurricular uc = ucOptional.get();
                c.getUnidadeCurricular().add(uc);
                cursoDAO.atualizarUC(c.getId(), c.getUnidadeCurricular());
                System.out.println("Unidade Curricular adicionada ao curso: " + c.getNome());
            }
        } else System.out.println("Curso não encontrado. Caso tenha sido cadastrado recentemente, " +
                "reinicie o sistema para adicionar a Unidade Curricular.");
    }

    public void removerUC() {
            /*System.out.println("ID do curso que deseja remover uma UC: ");
            int idCurso = scanner.nextInt();
            scanner.nextLine();

            // Busca o curso específico pelo ID
            Optional<Curso> cursoOptional = cursoDAO.buscarPorId(idCurso);

            if (cursoOptional.isPresent()) {
                System.out.println("Curso não encontrado.");
                Curso curso = cursoOptional.get();

                System.out.println("Digite o ID da UC que deseja remover do curso: ");
                System.out.println("--- Unidades Curriculares ---");
                for (UnidadeCurricular uc : controllerUC.listarUC()) {
                    System.out.printf("ID: %d | Nome: %s |\n", uc.getId(), uc.getNome());
                }
                int idUC = scanner.nextInt();
                scanner.nextLine();

                Optional<UnidadeCurricular> ucOptional = ucDAO.buscarPorId(idUC);

                if (ucOptional.isEmpty()) {
                    System.out.println("Unidade Curricular não encontrada.");
                    return;
                }

                UnidadeCurricular uc = ucOptional.get();

                // Remove da lista apenas do curso correto
                if (curso.getUnidadeCurricular().remove(uc.getNome())) {
                    System.out.println("Unidade Curricular removida: " + uc.getNome());
                    cursoDAO.atualizarUC(curso.getId(), curso.getUnidadeCurricular());
                } else System.out.println("Unidade Curricular não encontrada na lista do curso.");

            } else System.out.println("Curso não encontrado. Caso tenha sido cadastrado recentemente, " +
                    "reinicie o sistema para remover a Unidade Curricular desejada.");
    }*/
}