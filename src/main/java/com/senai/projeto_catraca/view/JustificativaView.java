
package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.JustificativaController;
import com.senai.projeto_catraca.model.usuario.aluno.Justificativa;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class JustificativaView {
    private final Scanner scanner = new Scanner(System.in);
    private final JustificativaController controller = new JustificativaController();

    public static void main(String[] args) {
        JustificativaView justificativaView = new JustificativaView();
        justificativaView.menu();
    }

    public void menu() {
        System.out.println("--Justificativas--");
        String menu = """
                 ______________________________
                 |1. Gerar Justificativa      |
                 |2. Deletar Justificativa    |
                 |3. Atualizar Justificativa  |
                 |4. Exibir Justificativa     |
                 |0. Sair                     |
                 |____________________________|
                """;

        int opcao;
        do {
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    deletar();
                    break;
                case 3:
                    atualizar();
                    break;
                case 4:
                    exibir();
                    break;
                case 0:
                    System.out.println("Encerrando o Sistema!...");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
                    break;
            }
        } while (opcao != 0);
        scanner.close();
    }

    public void cadastrar() {
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
            case 1:
                tipo = "Atestado Médico";
                break;
            case 2:
                tipo = "Atestado de Falecimento";
                break;
            case 3:
                tipo = "Atestado de Vacinação";
                break;
            case 4:
                tipo = "Atestado de Isolamento";
                break;
            case 5:
                tipo = "Outros";
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
        String anexo = "";

        if (resposta.equals("s")) {
            boolean anexoValido = false;

            while (!anexoValido) {
                System.out.print("Digite o nome ou caminho do anexo: ");
                anexo = scanner.nextLine();
                anexoValido = new File(anexo).exists();

                if (!anexoValido) {
                    System.out.println("Arquivo não encontrado. Tente novamente.");
                }
            }
        }
        controller.cadastrarJustificativa(tipo, descricao, data, anexo);
    }

    public void deletar() {
        if (controller.listarJustificativa().isEmpty()) {
            System.out.println("Não há Justificativas cadastradas!!");
        } else {
            System.out.println("--- Justificativas ---");
            controller.listarJustificativa().forEach(
                    j -> {
                        System.out.printf("ID: %d | Tipo: %s | Descrição: %s |Anexo: %s\n", j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo());
                    }
            );
            System.out.print("Digite o id do Justificativa que deseja deletar: ");
            int id = scanner.nextInt();
            controller.removerJustificativa(id);
        }
    }

    public void atualizar() {
        if (controller.listarJustificativa().isEmpty()) {
            System.out.println("Não há Justificativas cadastradas!!");
        } else {
            System.out.print("Digite o ID da Justificativa que deseja atualizar: ");
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
                case 1:
                    tipo = "Atestado Médico";
                    break;
                case 2:
                    tipo = "Atestado de Falecimento";
                    break;
                case 3:
                    tipo = "Atestado de Vacinação";
                    break;
                case 4:
                    tipo = "Atestado de Isolamento";
                    break;
                case 5:
                    tipo = "Outros";
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
            String anexo = "Sem anexo";

            if (resposta.equals("s")) {
                boolean anexoValido = false;

                while (!anexoValido) {
                    System.out.print("Digite o nome ou caminho do anexo: ");
                    anexo = scanner.nextLine();
                    anexoValido = new File(anexo).exists();

                    if (!anexoValido) {
                        System.out.println("Arquivo não encontrado. Tente novamente.");
                    }
                }
            }
            controller.atualizarJustificativa(tipo, descricao, data, id, anexo);
        }

    }

    public void exibirNaoAprovadas() {
        if (controller.listarJustificativa().isEmpty()) {
            System.out.println("Não há Justificativas cadastradas!!");
        } else {
            System.out.println("--- Justificativas ---");
            controller.listarJustificativa().forEach(
                    j -> {
                        if (j.getStatus().equals("Aguardando aprovação da AQV...")) {

                            System.out.printf("ID: %d | Tipo: %s | Descrição: %s | Anexo: %s | Status: %s\n",
                                    j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo(), j.getStatus());
                        }
                    }
            );
        }
        System.out.println("----!!SIMULAÇÃO!!----");
        System.out.println("Deseja aprovar alguma justificativa? (s/n)");
        String resposta = scanner.nextLine();
        if (resposta.equals("s")) {


            System.out.println("Digite o ID da justificativa para aprovar ou reprovar: ");
            int idJust = scanner.nextInt();
            scanner.nextLine();
            String status;
            int escolha;
            String menu = """
                    1. Aprovar
                    2. Reprovar
                    """;
            System.out.println(menu);
            escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1:
                    status = "Aprovado pela AQV. Aguarde ciência do professor.";
                    for (Justificativa j : controller.listarJustificativa()) {
                        if (j.getId() == idJust) {
                            String tipo = j.getTipo();
                            String descricao = j.getDescricao();
                            String data = j.getData();
                            String anexo = j.getAnexo();
                            controller.setStatus(tipo, descricao, data, idJust, anexo, status);
                        }
                    }
                    System.out.println("Aprovada com sucesso.");
                    break;

                case 2:
                    status = "Reprovado pela AQV.";
                    for (Justificativa j : controller.listarJustificativa()) {
                        if (j.getId() == idJust) {
                            String tipo = j.getTipo();
                            String descricao = j.getDescricao();
                            String data = j.getData();
                            String anexo = j.getAnexo();
                            controller.setStatus(tipo, descricao, data, idJust, anexo, status);
                        }
                    }
                    System.out.println("Reprovada com sucesso.");
                    break;
            }

        }

    }

    public void exibirNaoAprovadasProfessor() {
        if (controller.listarJustificativa().isEmpty()) {
            System.out.println("Não há Justificativas cadastradas!!");
        } else {
            System.out.println("--- Justificativas ---");
            controller.listarJustificativa().forEach(
                    j -> {
                        if (j.getStatus().equals("Aprovado pela AQV. Aguarde ciência do professor.")) {
                            System.out.printf("ID: %d | Tipo: %s | Descrição: %s | Anexo: %s | Status: %s\n",
                                    j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo(), j.getStatus());
                        }
                    }
            );
        }
        System.out.println("Deseja dar ciência para alguma justificativa? (s/n)");
        String resposta = scanner.nextLine();
        if (resposta.equals("s")) {
            System.out.println("Digite o ID da justificativa para dar ciência: ");
            int idJust = scanner.nextInt();
            scanner.nextLine();
            String status;
            status = "Aprovada.";
            for (Justificativa j : controller.listarJustificativa()) {
                if (j.getId() == idJust) {
                    String tipo = j.getTipo();
                    String descricao = j.getDescricao();
                    String data = j.getData();
                    String anexo = j.getAnexo();
                    controller.setStatus(tipo, descricao, data, idJust, anexo, status);
                }
            }
            System.out.println("Aprovada com sucesso.");
        }
    }

    public void exibir() {
        if (controller.listarJustificativa().isEmpty()) {
            System.out.println("Não há Justificativas cadastradas!!");
        } else System.out.println("--- Justificativas ---");
        controller.listarJustificativa().forEach(
                j -> {
                    System.out.printf("ID: %d | Tipo: %s | Descrição: %s | Anexo: %s | Status: %s\n",
                            j.getId(), j.getTipo(), j.getDescricao(), j.getAnexo(), j.getStatus());
                }
        );
    }
}
