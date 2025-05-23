package com.senai.projeto_catraca.view;

import java.util.Scanner;

public class ProfessorView {
    public static void main(String[] args) {
        System.out.printf("Bem vindo");
        Scanner scanner = new Scanner(System.in);
        executarMenu("""
                    ===== MENU PROFESSOR =====
                    1. Gerenciar Horários
                    2. Receber notificações de atraso
                    3. Deslogar
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> System.out.println("Conectar");
                        case "2" -> System.out.println("Opção 2");
                        case "3" -> System.out.println("Opção 3");
                        case "0" -> {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                        default  -> System.out.println("Opção inválida.");
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
