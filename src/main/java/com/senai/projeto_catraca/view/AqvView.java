package com.senai.projeto_catraca.view;


import com.senai.projeto_catraca.controller.AqvController;
import com.senai.projeto_catraca.model.usuario.AQV;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.Scanner;

public class AqvView {
    private final Scanner scanner = new Scanner(System.in);
    private final AqvController controller = new AqvController();

    public static void main(String[] args) {
        AqvView aqvView = new AqvView();
        aqvView.menu();
    }
    public void menu(){
        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de AQV:                                    |
                                        |           1- Cadastrar Aqv                            |
                                        |           2- Atualizar Aqv                            |
                                        |           3- Listar Aqv                               |
                                        |           4- Deletar Aqv                              |
                                        |           5- Sair                                     |
                                        |_______________________________________________________|
                    
                    """;
            System.out.println(menu);
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoMenu) {
                case 1:
                    String nome = scannerPrompt("|Nome: ");
                    String CPF = scannerPrompt("|CPF:");
                    String telefone = scannerPrompt("|Telefone:");
                    String endereco= scannerPrompt("|Endereço:");
                    String senha = scannerPrompt("|Senha:");
                    System.out.println(controller.cadastrarAqv(nome,senha,CPF,endereco,telefone));
                    break;
                case 2:
                    int id = scannerPromptInt("Informe o ID do AQV que deseja atualizar\n|ID:");
                    String nomeN = scannerPrompt("|Novo Nome: ");
                    String CPFN = scannerPrompt("|Novo CPF:");
                    String telefoneN = scannerPrompt("|Novo Telefone:");
                    String enderecoN = scannerPrompt("|Novo Endereço:");
                    String senhaN = scannerPrompt("|Nova Senha:");
                    System.out.println(controller.atualizarAqv(nomeN, senhaN,CPFN,id, enderecoN, telefoneN));
                    break;
                case 3:
                    System.out.println("Analistas de Qualidade de Vida:");
                    for (AQV aqv: controller.listarAqv()) {
                        System.out.printf("|ID: %d | Nome: %s\n", aqv.getId(), aqv.getNome());
                    }
                    break;
                case 4:
                    int idD = scannerPromptInt("Informe o ID do AQV que deseja deletar\n|ID:");
                    System.out.println(controller.deletarAqv(idD));
                    break;
                case 5:
                    System.out.println("Fim de Programa!");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }while (opcaoMenu !=5);
    }
    private String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

}
