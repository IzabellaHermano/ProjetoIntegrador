package com.senai.projeto_catraca.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.senai.projeto_catraca.controller.ProfessorController;
import com.senai.projeto_catraca.model.usuario.Professor;

public class ProfessorView {
    private static final ProfessorController professorController = new ProfessorController();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ProfessorView professorView = new ProfessorView();
        professorView.menu();
    }
    public void menu() {
        System.out.printf("Bem vindo");
        executarMenu("""
                    ===== MENU PROFESSOR =====
                    1. Cadastrar professor
                    2. Listar Professor
                    3. Remover Professor
                    4. Atualizar Professor
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> cadastrarProfessor();
                        case "2" -> listarProfessores();
                        case "3" -> removerProfessor();
                        case "4" -> atualizarProfessor();
                        default -> System.out.println("Opção inválida!");
                    }
                });
    }

    private static void cadastrarProfessor() {
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.println("Digite uma senha: ");
        String senha = scanner.nextLine();
        System.out.println("Digite uma cpf: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite um endereço: ");
        String endereco = scanner.nextLine();
        System.out.println("Digite uma disciplina: ");
        String disciplina = scanner.nextLine();
        System.out.println("Digite um telefone: ");
        String telefone = scanner.nextLine();

        professorController.cadastrarProfessor(nome, senha, cpf, endereco, telefone, disciplina);
        
        System.out.println("Professor cadastrado com sucesso!");
        System.out.println("Nome: " + nome);
        System.out.println("Disciplina: " + disciplina);
        System.out.println("Endereço: " + endereco);
    }

    private static void listarProfessores() {
        List<Professor> listaProfessores = professorController.listarTodosProfessores();

        if (listaProfessores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            System.out.println("Lista de Professores:");
            for (Professor professor : listaProfessores) {
                System.out.println("ID: " + professor.getId() + 
                                 ", Nome: " + professor.getNome() + 
                                 ", Disciplina: " + professor.getDisciplina());
            }
        }
    }

    private static void removerProfessor() {
        System.out.println("Remover Professor");
        System.out.println("Qual o id do professor que deseja remover?");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (professorController.removerProfessor(id)) {
            System.out.println("Professor removido com sucesso!");
        } else {
            System.out.println("Professor com ID " + id + " não encontrado.");
        }
    }

    private static void atualizarProfessor() {
        System.out.println("Atualizar Professor");
        System.out.println("Qual o ID do professor que deseja atualizar?");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Optional<Professor> professorOpt = professorController.buscarProfessorPorId(id);
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            
            System.out.println("Digite o novo nome (atual: " + professor.getNome() + "):");
            String nome = scanner.nextLine();
            System.out.println("Digite a nova senha (atual: " + professor.getSenha() + "):");
            String senha = scanner.nextLine();
            System.out.println("Digite o novo CPF (atual: " + professor.getCPF() + "):");
            String cpf = scanner.nextLine();
            System.out.println("Digite o novo endereço (atual: " + professor.getEndereco() + "):");
            String endereco = scanner.nextLine();
            System.out.println("Digite o novo telefone (atual: " + professor.getTelefone() + "):");
            String telefone = scanner.nextLine();
            System.out.println("Digite a nova disciplina (atual: " + professor.getDisciplina() + "):");
            String disciplina = scanner.nextLine();

            if (professorController.atualizarProfessor(id, nome, senha, cpf, endereco, telefone, disciplina)) {
                System.out.println("Professor atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar professor.");
            }
        } else {
            System.out.println("Professor com ID " + id + " não encontrado.");
        }
    }

    private static void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
        String opcao;
        do {
            System.out.print(titulo);
            opcao = scanner.nextLine();
            acoes.accept(opcao);
        } while (!opcao.equals("0"));
    }
}