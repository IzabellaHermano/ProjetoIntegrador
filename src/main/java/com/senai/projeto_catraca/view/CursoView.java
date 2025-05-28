package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.CursoController;
import com.senai.projeto_catraca.controller.UCController;
import com.senai.projeto_catraca.model.curso.Curso;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;
import com.senai.projeto_catraca.model.curso.UnidadeCurricularDAO;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CursoView {
    private ArrayList<String> listaUCs = new ArrayList<>();
    private final CursoController controllerCurso = new CursoController();
    private final UCController controllerUC = new UCController();
    private final Scanner scanner = new Scanner(System.in);
    private final UnidadeCurricularDAO ucDAO = new UnidadeCurricularDAO();

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
                 |      6. Remover unidade currricular         |
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
                    adicionarUC();
                    break;
                case 6:
                    removerUC();
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

        ArrayList<String> listaUCs = null;
        controllerCurso.cadastrarCurso(nome, tipo, duracao, listaUCs);
        System.out.println("Curso cadastrado com sucesso!");
    }

    public void atualizar() {
        System.out.print("Digite o ID do Coordenador que deseja atualizar: ");
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

        ArrayList<String> listaUCs = null;
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

    public void adicionarUC() {
        System.out.println("ID do curso que deseja adicionar a UC: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ID da UC que deseja adicionar ao curso: ");
        System.out.println("--- Unidades Curriculares ---");
        for (UnidadeCurricular uc : controllerUC.listarUC()) {
            System.out.printf("ID: %d | Nome: %s |\n", uc.getId(), uc.getNome());
        }
        int idUC = scanner.nextInt();
        scanner.nextLine();

        //código desenvolvido para pegar a matéria pelo ID e colocar apenas o nome na lista de UCs do curso.
        Optional<UnidadeCurricular> ucOptional = ucDAO.buscarPorId(idUC);
        UnidadeCurricular uc = ucOptional.get();
        listaUCs.add(uc.getNome());
        System.out.println("Unidade Curricular adicionada: " + uc.getNome());


        controllerCurso.addUC(listaUCs, idCurso);
    }

    public void removerUC() {
        System.out.println("ID do curso que deseja remover uma UC: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ID da UC que deseja remover do curso: ");
        System.out.println("--- Unidades Curriculares ---");
        for (UnidadeCurricular uc : controllerUC.listarUC()) {
            System.out.printf("ID: %d | Nome: %s |\n", uc.getId(), uc.getNome());
        }
        int idUC = scanner.nextInt();
        scanner.nextLine();

        Optional<UnidadeCurricular> ucOptional = ucDAO.buscarPorId(idUC);

        UnidadeCurricular uc = ucOptional.get();
        listaUCs.remove(uc.getNome());
        System.out.println("Unidade Curricular removida: " + uc.getNome());

        controllerCurso.addUC(listaUCs, idCurso);
    }

}