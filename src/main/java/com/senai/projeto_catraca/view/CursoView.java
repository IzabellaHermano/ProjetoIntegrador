package com.senai.projeto_catraca.view;

import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.senai.projeto_catraca.controller.CursoController;
import com.senai.projeto_catraca.model.curso.Curso;
import com.senai.projeto_catraca.model.curso.CursoDAO;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;

import java.util.*;

public class CursoView {
    private final ArrayList<UnidadeCurricular> listaUCs = new ArrayList<>();
    private final CursoController controllerCurso = new CursoController();
    private final Scanner scanner = new Scanner(System.in);
    private final CursoDAO cursoDAO = new CursoDAO();


    public static void main(String[] args) {
        CursoView cursoView = new CursoView();
        cursoView.menu();
    }

    public void menu() {
        System.out.println("--Cursos--");
        String menu = """
                 _______________________________________________
                 |      1. Adicionar cursos                    |
                 |      2. Atualizar cursos                    |
                 |      3. Remover cursos                      |
                 |      4. Listar cursos                       |
                 |      5. Adicionar unidades curiculares      |
                 |      6. Sair                                |
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
                    menuUC();
                    break;
                case 6:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
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
                2. CAI
                """);
        int resposta = scanner.nextInt();
        scanner.nextLine();
        if (resposta == 1) {
            tipo = "Técnico";
        } else tipo = "CAI";

        controllerCurso.cadastrarCurso(nome, tipo, duracao, listaUCs);
        System.out.println("Curso cadastrado com sucesso! Reinicie o sistema para salvar as alterações!");
    }

    public void atualizar() {
        if (cursoDAO.listar().isEmpty()) {
            System.out.println("Não há cursos cadastrados!");
        } else {
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
                    2. CAI
                    """);
            int resposta = scanner.nextInt();
            scanner.nextLine();
            if (resposta == 1) {
                tipo = "Técnico";
            } else tipo = "CAI";

            controllerCurso.atualizarCurso(nome, tipo, duracao, id, listaUCs);
            System.out.println("Curso atualizado com sucesso!");
        }
    }

    public void deletar() {
        if (controllerCurso.listarCurso().isEmpty()) {
            System.out.println("Não há cursos cadastrados!");
        } else {
            System.out.println("--- Cursos ---");
            for (Curso cs : controllerCurso.listarCurso()) {
                System.out.printf("ID: %d | Nome: %s\n",
                        cs.getId(), cs.getNome());
            }
            System.out.println("Digite o id do curso que deseja deletar: ");
            int id = scanner.nextInt();
            controllerCurso.removerCurso(id);
            System.out.println("Curso deletado com sucesso.");
        }
    }

    public void listar() {
        if (controllerCurso.listarCurso().isEmpty()) {
            System.out.println("Não há cursos cadastrados!");
        } else {
            System.out.println("--- Cursos ---");
            controllerCurso.listarCurso().forEach(
                    curso -> {
                        System.out.printf("Id: %d | Nome: %s | | Duração: %s anos | Tipo: %s\n", curso.getId(), curso.getNome(), curso.getDuracao(), curso.getTipo());
                        curso.getListaUnidadeCurricular().forEach(
                                uc -> System.out.printf(" -> Unidades Curriculares [ID: %d | Nome: %s | Carga Horaria: %s]\n", uc.getId(), uc.getNome(), uc.getCargaHoraria())
                        );
                    }
            );
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
                 |5. Voltar                          |
                 |___________________________________|
                """;
        String menu2 = """
                 _____________________________________
                 |1. Cadastrar Unidade Curricular    |
                 |2. Deletar Unidade Curricular      |
                 |3. Voltar                          |
                 |___________________________________|
                """;

        int opcao;
        do {
            System.out.println(menu2);
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
                    return;
                default:
                    System.out.println("Digite uma opção válida.");
            }
        } while (true);

    }

    public void cadastrarUC() {
        if (controllerCurso.listarCurso().isEmpty()) {
            System.out.println("Cadastre um curso para adicionar uma Unidade Curricular!");
        } else {
            System.out.println("Digite o ID do curso em que deseja cadastrar esta Unidade Curricular: ");
            int idCurso = scanner.nextInt();
            scanner.nextLine();
            Optional<Curso> cursoOpt = cursoDAO.buscarPorId(idCurso);
            if (cursoOpt.isPresent()) {
                Curso c = cursoOpt.get();

                System.out.println("Digite as seguintes informações: ");
                System.out.print("Carga horaria (HH:mm): ");
                String cargaHoraria = scanner.nextLine();
                System.out.print("Nome: ");
                String nome = scanner.nextLine();

                c.getListaUnidadeCurricular().add(controllerCurso.cadastrarUC(idCurso, cargaHoraria, nome));

                cursoDAO.atualizar(c);
            }
        }
    }

    public void deletarUC() {
        if (controllerCurso.listarCurso().isEmpty()) {
            System.out.println("Cadastre um curso para deletar uma Unidade Curricular!");
        } else {
            System.out.println("Digite o ID do curso em que deseja cadastrar esta Unidade Curricular: ");
            System.out.println("--- Cursos ---");
            controllerCurso.listarCurso().forEach(
                    curso -> {
                        System.out.printf("Id: %d | Nome: %s \n", curso.getId(), curso.getNome());
                    }
            );
            int idCurso = scanner.nextInt();
            scanner.nextLine();
            Optional<Curso> cursoOpt = cursoDAO.buscarPorId(idCurso);
            if (cursoOpt.isPresent()) {
                Curso c = cursoOpt.get();

                System.out.println("--- Unidades Curriculares ---");
                c.getListaUnidadeCurricular().forEach(
                        uc -> System.out.printf(" -> Unidades Curriculares [ID: %d | Nome: %s]\n", uc.getId(), uc.getNome())
                );

                System.out.println("Digite o id da Unidade Curricular que deseja deletar: ");
                int idUC = scanner.nextInt();
                controllerCurso.removerUC(idCurso, idUC);
                cursoDAO.atualizar(c);
            }
        }
    }
}

  /*  public void atualizarUC() {
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
            System.out.printf("ID: %d | Nome: %s | Carga Horaria: %s\n",
                    uc.getId(), uc.getNome(), uc.getCargaHoraria());
        }
    }
    */