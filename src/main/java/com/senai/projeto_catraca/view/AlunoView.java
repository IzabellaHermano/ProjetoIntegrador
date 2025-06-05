package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.AlunoController;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.Scanner;

public class AlunoView {
    private final Scanner scanner = new Scanner(System.in);
    private final AlunoController controller = new AlunoController();

    public static void main(String[] args) {
        AlunoView alunoView = new AlunoView();
        alunoView.menu();
    }
    public void menu(){

        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de Aluno:                                  |
                                        |           1- Cadastrar Alunos                         |
                                        |           2- Atualizar Alunos                         |
                                        |           3- Listar Alunos                            |
                                        |           4- Buscar Alunos Por ID                     |
                                        |           5- Deletar Alunos                           |
                                        |           6- Sair                                     |
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
                    int matricula = scannerPromptInt("|Matricula:");
                    int rfid= scannerPromptInt("|ID do cartão RFID:");
                    String senha = scannerPrompt("|Senha:");
                    System.out.println(controller.cadastrarAluno(nome,senha,CPF,endereco,telefone,matricula,rfid));
                    break;
                case 2:
                    int id = scannerPromptInt("Informe o ID do aluno que deseja atualizar\n|ID:");
                    String nomeN = scannerPrompt("|Novo Nome: ");
                    String CPFN = scannerPrompt("|Novo CPF:");
                    String telefoneN = scannerPrompt("|Novo Telefone:");
                    String enderecoN = scannerPrompt("|Novo Endereço:");
                    int matriculaN = scannerPromptInt("|Nova Matricula:");
                    int rfidN = scannerPromptInt("|Novo ID do cartão RFID:");
                    String senhaN = scannerPrompt("|Nova Senha:");
                    System.out.println(controller.atualizarAluno(nomeN, senhaN,CPFN,id, enderecoN, telefoneN, matriculaN, rfidN));
                    break;
                case 3:
                    System.out.println("ALUNOS:");
                    for (Aluno a : controller.listarAlunos()) {
                        System.out.printf("ID: %d | Nome: %s | RFID: %s\n", a.getId(), a.getNome(), a.getIdCartaoRfid());
                    }
                    break;
                case 4:
                    int idB = scannerPromptInt("Informe o ID do aluno que deseja buscar\n|ID:");
                    System.out.println(controller.buscarPorID(idB));
                    break;
                case 5:
                    int idD = scannerPromptInt("Informe o ID do aluno que deseja deletar\n|ID:");
                    System.out.println(controller.deletarAluno(idD));
                    break;
                case 6:
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

