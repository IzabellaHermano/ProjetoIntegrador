package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.JustificativaController;
import com.senai.projeto_catraca.model.usuario.aluno.Justificativa;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class JustificativaView {
    private final Scanner scanner = new Scanner(System.in);
    private final JustificativaController controller = new JustificativaController();
    public static void main(String[] args) {
        JustificativaView justificativaView = new JustificativaView();
        justificativaView.menu();
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
        System.out.print("""
                1.tipo1
                2.tipo2
                3.tipo3
                4.tipo4
                5.Outros/
                """);
        int escolha = scanner.nextInt();
        scanner.nextLine();
        String tipo = "";
        switch (escolha) {
            case 1: tipo = "tipo1";
            break;
            case 2: tipo = "tipo2";
            break;
            default:
                System.out.println("Digite uma opção válida.");
        }
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data: ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Anexo: ");
        String anexo = scanner.nextLine();
        controller.cadastrarJustificativa(tipo, descricao, data, anexo);
    }
    public void deletar(){
        System.out.println("--- Justificativas ---");
        for (Justificativa j : controller.listarJustificativa()) {
            System.out.printf("ID: %d | Tipo: %s | Descrição: %s |Anexo: %s\n", j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo());
        }
        System.out.print("Digite o id do coordenador que deseja deletar: ");
        int id = scanner.nextInt();
        controller.removerJustificativa(id);
    }
    public void atualizar(){
        System.out.print("Digite o ID do Coordenador que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite as seguintes informações: ");
        System.out.print("""
                1.tipo1
                2.tipo2
                3.tipo3
                4.tipo4
                5.Outros/
                """);
        int escolha = scanner.nextInt();
        String tipo = "";
        switch (escolha) {
            case 1: tipo = "tipo1";
                break;
            case 2: tipo = "tipo2";
                break;
            default:
                System.out.println("Digite uma opção válida.");
        }
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data: ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Anexo: ");
        String anexo = scanner.nextLine();
        controller.atualizarJustificativa(tipo, descricao, data, anexo, id);
    }
    public void exibir(){
        if (controller.listarJustificativa().isEmpty()){
            System.out.println("Não há Justificativas cadastradas!!");
        } else System.out.println("--- Justificativas ---");
        for (Justificativa j : controller.listarJustificativa()) {
            System.out.printf("ID: %d | Tipo: %s | Descrição: %s |Anexo: %s\n", j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo());
        }
    }
}
