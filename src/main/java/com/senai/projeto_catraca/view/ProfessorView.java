package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.model.dao.json.ProfessorDAO;
import com.senai.projeto_catraca.model.usuario.Professor;

import java.util.Scanner;

public class ProfessorView {
    public static void main(String[] args) {
        System.out.printf("Bem vindo");
        Scanner scanner = new Scanner(System.in);
        ProfessorDAO professorDAO = new ProfessorDAO();
        executarMenu("""
                    ===== MENU PROFESSOR =====
                    1. Cadastrar professor
                    2. Listar Professor
                    3. Deslogar
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> {
                            System.out.println("Digite o nome: ");
                            String nome = scanner.nextLine();
                            System.out.println("Digite uma senha: ");
                            String senha = scanner.nextLine();
                            System.out.println("Digite uma cpf: ");
                            String cpf = scanner.nextLine();
                            System.out.println("Digite um endereço: ");
                            String endereco = scanner.nextLine();
                            System.out.println("Digite um disciplina: ");
                            String disciplina = scanner.nextLine();
                            System.out.println("Digite uma telefone: ");
                            String telefone = scanner.nextLine();
                            Professor novoProfessor = new Professor(nome,senha,cpf,0,endereco,telefone,disciplina);
                            professorDAO.inserir(novoProfessor);
                            System.out.println("Professor cadastrado com sucesso!");
                            System.out.println("Nome: " + novoProfessor.getNome());
                            System.out.println("Disciplina: " + novoProfessor.getDisciplina());
                            System.out.println("Endereço: " + endereco);
                        }
                        case "2" -> {
                            professorDAO.listarTodos();
                        }
                        case "3" -> {
                            System.out.println("Remover");
                        }
                        default -> {
                            System.out.println("Opção inválida!");
                        }
                    }
                });
    }
private static void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
    String opcao;
    Scanner scanner = new Scanner(System.in);
    do {
        System.out.print(titulo);
        opcao = scanner.nextLine();
        acoes.accept(opcao);
    } while (!opcao.equals("0"));
}
}
