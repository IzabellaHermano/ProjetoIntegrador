package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.CoordenadorController;
import com.senai.projeto_catraca.model.usuario.Coordenador;

import java.util.Scanner;

public class CoordenadorView {
    private final CoordenadorController controller = new CoordenadorController();
    private final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        CoordenadorView coordenadorView = new CoordenadorView();
        coordenadorView.menu();
    }
    public void menu(){
        System.out.println("--Coordenador--");
        String menu = """
                ______________________________
                |1. Cadastrar Coordenador    |
                |2. Deletar Coordenador      |
                |3. Atualizar Coordenador    |
                |4. Exibir Coordenador       |
                |5. Sair                     |
                |____________________________|
               """;

        int opcao = 0;
        do {
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1: cadastrar();
                break;
                case 2: deletar();
                break;
                case 3: atualizar();
                break;
                case 4: exibir();
                break;
                case 5:
                    System.out.println("Encerrando o Sistema!...");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
                    break;
            }
        } while (opcao != 5);

    }

    public void cadastrar(){
        System.out.println("Digite as seguintes informações: ");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("CPF: ");
        String CPF = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        controller.cadastrarCoordenador(nome, senha, CPF, endereco, telefone);
    }
    public void deletar(){
        System.out.println("--- Coordenadores ---");
        for (Coordenador c : controller.listarCoordenadores()) {
            System.out.printf("ID: %d | Nome: %s | CPF: %s\n", c.getId(), c.getNome(), c.getCPF());
        }
        System.out.print("Digite o id do coordenador que deseja deletar: ");
        int id = scanner.nextInt();
        controller.removerCoordenador(id);
    }
    public void atualizar(){
        System.out.print("Digite o ID do Coordenador que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite as seguintes informações: ");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String CPF = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        controller.atualizarCoordenador(id, nome, senha, CPF, endereco, telefone);
    }
    public void exibir(){
        if (controller.listarCoordenadores().isEmpty()){
            System.out.println("Não há coordenadores cadastrados!!");
        } else System.out.println("--- Coordenadores ---");
        for (Coordenador c : controller.listarCoordenadores()) {
            System.out.printf("ID: %d | Nome: %s | Telefone: %s | CPF: %s\n", c.getId(), c.getNome(), c.getTelefone(), c.getCPF());
        }
    }
}
