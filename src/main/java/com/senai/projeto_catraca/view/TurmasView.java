package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.turma.TurmasDAO;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TurmasView {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TurmasDAO turmasDAO = new TurmasDAO();

        executarMenu("""
                ===== MENU TURMA =====
                1. Cadastrar Turma
                2. Listar Turmas
                3. Remover Turma
                4. Adicionar Aluno à Turma
                0. Sair
                """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> {
                            System.out.println("Digite o nome da turma:");
                            String nome = scanner.nextLine();
                            System.out.println("Digite o turno (Manhã/Tarde/Noite):");
                            String turno = scanner.nextLine();
                            System.out.println("Digite o ID do professor responsável:");
                            int professorId = scanner.nextInt();
                            scanner.nextLine();

                            Turmas novaTurma = new Turmas(nome, turno, professorId);
                            turmasDAO.inserir(novaTurma);
                            System.out.println("Turma criada com sucesso!");
                        }
                        case "2" -> {
                            List<Turmas> turmas = turmasDAO.listarTodas();
                            if (turmas.isEmpty()) {
                                System.out.println("Nenhuma turma cadastrada.");
                            } else {
                                for (Turmas t : turmas) {
                                    System.out.println("ID: " + t.getId() + ", Nome: " + t.getNome() + ", Turno: " + t.getTurno() + ", ProfessorID: " + t.getProfessorId());
                                }
                            }
                        }
                        case "3" -> {
                            System.out.println("Digite o ID da turma para remover:");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            turmasDAO.remover(id);
                            System.out.println("Turma removida com sucesso.");
                        }
                        case "4" -> {
                            System.out.println("Digite o ID da turma para adicionar aluno:");
                            int idTurma = scanner.nextInt();
                            scanner.nextLine();
                            Optional<Turmas> turmaOpt = turmasDAO.buscarPorId(idTurma);
                            if (turmaOpt.isPresent()) {
                                System.out.println("Digite a matrícula do aluno:");
                                int matricula = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Aluno adicionado à turma com sucesso.");
                            } else {
                                System.out.println("Turma com ID " + idTurma + " não encontrada.");
                            }
                        }
                        case "0" -> System.out.println("Saindo...");
                        default -> System.out.println("Opção inválida.");
                    }
                });
    }

    private static void executarMenu(String menu, java.util.function.Consumer<String> acoes) {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        do {
            System.out.print(menu);
            opcao = scanner.nextLine();
            acoes.accept(opcao);
        } while (!opcao.equals("0"));
    }
}
