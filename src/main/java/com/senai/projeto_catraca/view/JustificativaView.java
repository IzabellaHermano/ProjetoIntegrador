package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.JustificativaController;
import com.senai.projeto_catraca.model.usuario.aluno.Justificativa;

import java.io.File;
import java.util.Scanner;

public class JustificativaView {
    private final Scanner scanner = new Scanner(System.in);
    private final JustificativaController controller = new JustificativaController();
    public static void main(String[] args) {
        JustificativaView justificativaView = new JustificativaView();
        justificativaView.menu();
    }
    public void menu(){
        System.out.println("--Justificativas--");
        String menu = """
                ______________________________
                |1. Gerar Justificativa      |
                |2. Deletar Justificativa    |
                |3. Atualizar Justificativa  |
                |4. Exibir Justificativa     |
                |6. Sair                     |
                |____________________________|
               """;

        int opcao;
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
                    exibirNaoAprovadas();
                    break;
                case 6:
                    System.out.println("Encerrando o Sistema!...");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
                    break;
            }
        } while (opcao != 6);

    }

    public void cadastrar(){
        System.out.println("Digite as seguintes informações: ");
        System.out.print("""
               -- Qual o tipo de justificativa? --
               
                1. Atestado Médico
                2. Atestado de Falecimento
                3. Atestado de Vacinação
                4. Atestado de Isolamento
                5. Outros
               """);
        int escolha = scanner.nextInt();
        scanner.nextLine();
        String tipo = "";
        switch (escolha) {
            case 1: tipo = "Atestado Médico";
                break;
            case 2: tipo = "Atestado de Falecimento";
                break;
            case 3: tipo = "Atestado de Vacinação";
                break;
            case 4: tipo = "Atestado de Isolamento";
                break;
            case 5: tipo = "Outros";
                break;
            default:
                System.out.println("Digite uma opção válida.");
        }
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data (dd-mm-aaaa): ");
        String data = scanner.nextLine();

        // Parte do arquivo de anexo
        System.out.println("Deseja adicionar um anexo? (s/n):");
        String resposta = scanner.nextLine();
        if (resposta.equals("s")){
            controller.addAnexo(resposta);
            String anexo;
            System.out.println("Digite o nome ou caminho do anexo (ou digite 1 para voltar):");
            anexo = scanner.nextLine();
            controller.addAnexo(anexo);
            controller.cadastrarJustificativa(tipo, descricao, data, anexo);
        } else {
            System.out.println("Nenhum anexo adicionado.");
        }
    }

    public void deletar(){
        if (controller.listarJustificativa().isEmpty()){
            System.out.println("Não há Justificativas cadastradas!!");
        } else {
            System.out.println("--- Justificativas ---");
            for (Justificativa j : controller.listarJustificativa()) {
                System.out.printf("ID: %d | Tipo: %s | Descrição: %s |Anexo: %s\n", j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo());
            }
            System.out.print("Digite o id do Justificativa que deseja deletar: ");
            int id = scanner.nextInt();
            controller.removerJustificativa(id);
        }
    }
    public void atualizar(){
        if (controller.listarJustificativa().isEmpty()){
            System.out.println("Não há Justificativas cadastradas!!");
        } else { System.out.print("Digite o ID da Justificativa que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Digite as seguintes informações: ");
            System.out.print("""
                1. Atestado Médico
                2. Atestado de Falecimento
                3. Atestado de Vacinação
                4. Atestado de Isolamento
                5. Outros
                """);
            int escolha = scanner.nextInt();
            scanner.nextLine();
            String tipo = "";
            switch (escolha) {
                case 1: tipo = "Atestado Médico";
                    break;
                case 2: tipo = "Atestado de Falecimento";
                    break;
                case 3: tipo = "Atestado de Vacinação";
                    break;
                case 4: tipo = "Atestado de Isolamento";
                    break;
                case 5: tipo = "Outros";
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
            }
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Data: ");
            String data = scanner.nextLine();

            // Parte do arquivo de anexo
            System.out.println("Deseja adicionar um anexo? (s/n):");
            String resposta = scanner.nextLine();
            if (resposta.equals("s")){
                controller.addAnexo(resposta);
                String anexo;
                System.out.println("Digite o nome ou caminho do anexo (ou digite 1 para voltar):");
                anexo = scanner.nextLine();
                controller.addAnexo(anexo);
                controller.atualizarJustificativa(tipo, descricao, data, anexo, id);
            } else {
                System.out.println("Nenhum anexo adicionado.");
            }

        }
    }

    public void exibirNaoAprovadas() {
        if (controller.listarJustificativa().isEmpty()) {
            System.out.println("Não há Justificativas cadastradas!!");
        } else {
            for (Justificativa j : controller.listarJustificativa()) {
                if (j.getStatus().equals("Aguardando aprovação da AQV...")) {
                    System.out.println("--- Justificativas ---");
                    System.out.printf("ID: %d | Tipo: %s | Descrição: %s | Anexo: %s | Status: %s\n", j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo(), j.getStatus());
                }
            }
        }
    }
    public void exibir(){
        if (controller.listarJustificativa().isEmpty()){
            System.out.println("Não há Justificativas cadastradas!!");
        } else System.out.println("--- Justificativas ---");
        for (Justificativa j : controller.listarJustificativa()) {
            System.out.printf("ID: %d | Tipo: %s | Descrição: %s | Anexo: %s | Status: %s\n", j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo(), j.getStatus());
        }



    }
}