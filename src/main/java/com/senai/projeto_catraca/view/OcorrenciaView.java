package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.model.usuario.aluno.Ocorrencia;
import com.senai.projeto_catraca.controller.OcorrenciaController;

import java.util.List;
import java.util.Scanner;

public class OcorrenciaView {
    private final OcorrenciaController ocorrenciaController = new OcorrenciaController();

    public static void main(String[] args) {
        OcorrenciaView view = new OcorrenciaView();
        view.executarMenu();
    }

    private void executarMenu() {
        Scanner scanner = new Scanner(System.in);

        executarMenu("""
                ===== MENU OCORRÊNCIA =====
                1. Adicionar Ocorrência
                2. Listar Ocorrências
                3. Atualizar Ocorrência
                4. Remover Ocorrência
                0. Sair
                """, opcao -> {
            switch (opcao) {
                case "1" -> {
                    System.out.println("Digite o ID da ocorrência:");
                    int id = Integer.parseInt(scanner.nextLine());

                    System.out.println("Digite o ID do aluno:");
                    int alunoId = Integer.parseInt(scanner.nextLine());

                    System.out.println("Digite o ID da justificativa:");
                    int justificativaId = Integer.parseInt(scanner.nextLine());

                    System.out.println("Digite a data e hora (ex: 2024-05-29 14:30):");
                    String dataHora = scanner.nextLine();

                    Ocorrencia ocorrencia = new Ocorrencia(id, alunoId, justificativaId, dataHora);

                    if (ocorrenciaController.adicionarOcorrencia(ocorrencia)) {
                        System.out.println("Ocorrência adicionada com sucesso!");
                    } else {
                        System.out.println("Erro ao adicionar ocorrência.");
                    }
                }

                case "2" -> {
                    List<Ocorrencia> lista = ocorrenciaController.listarOcorrencias();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma ocorrência cadastrada.");
                    } else {
                        System.out.println("Lista de Ocorrências:");
                        for (Ocorrencia o : lista) {
                            System.out.printf("ID: %d | AlunoID: %d | JustificativaID: %d | DataHora: %s%n",
                                    o.getId(), o.getAlunoID(), o.getJustificativaID(), o.getDataHora());
                        }
                    }
                }

                case "3" -> {
                    System.out.println("Digite o ID da ocorrência a ser atualizada:");
                    int id = Integer.parseInt(scanner.nextLine());
                    Ocorrencia ocorrencia = ocorrenciaController.buscarOcorrenciaPorId(id);

                    if (ocorrencia != null) {
                        System.out.println("Digite o novo ID do aluno (atual: " + ocorrencia.getAlunoID() + "):");
                        int novoAlunoId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Digite o novo ID da justificativa (atual: " + ocorrencia.getJustificativaID() + "):");
                        int novaJustificativaId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Digite a nova data e hora (atual: " + ocorrencia.getDataHora() + "):");
                        String novaDataHora = scanner.nextLine();

                        ocorrencia.setAlunoID(novoAlunoId);
                        ocorrencia.setJustificativaID(novaJustificativaId);
                        ocorrencia.setDataHora(novaDataHora);

                        if (ocorrenciaController.atualizarOcorrencia(ocorrencia)) {
                            System.out.println("Ocorrência atualizada com sucesso.");
                        } else {
                            System.out.println("Erro ao atualizar a ocorrência.");
                        }
                    } else {
                        System.out.println("Ocorrência não encontrada.");
                    }
                }

                case "4" -> {
                    System.out.println("Digite o ID da ocorrência a ser removida:");
                    int id = Integer.parseInt(scanner.nextLine());

                    if (ocorrenciaController.removerOcorrencia(id)) {
                        System.out.println("Ocorrência removida com sucesso.");
                    } else {
                        System.out.println("Ocorrência não encontrada.");
                    }
                }

                case "0" -> System.out.println("Saindo...");

                default -> System.out.println("Opção inválida.");
            }
        });
    }

    private void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        do {
            System.out.println(titulo);
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();
            acoes.accept(opcao);
        } while (!opcao.equals("0"));
    }
}