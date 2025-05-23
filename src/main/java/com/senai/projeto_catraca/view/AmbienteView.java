package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.AmbienteController;
import com.senai.projeto_catraca.controller.TurmaController;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.turma.horario.Ambiente;

import java.util.Scanner;

public class AmbienteView {
    private final Scanner scanner = new Scanner(System.in);
    private final AmbienteController controller = new AmbienteController();

    public static void main(String[] args) {
        AmbienteView ambienteView = new AmbienteView();
        ambienteView.menu();
    }
    public void menu() {
        String opcao;
        String menuTurma = """
                   1 - Cadastrar Ambiente
                   2 - Atualizar Ambientes
                   3 - Remover Ambientes
                   4 - Listar Ambientes
                   0 - Sair.
                """;
        do {
            System.out.println(menuTurma);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }
    private void cadastrar(){
        String Nome = scannerPrompt("Nome do ambiente: ");
        int Numero = scannerPromptInt("Número do ambiente: ");
        System.out.println(controller.cadastrarAmbiente(Nome, Numero));
    }
    private void atualizar(){
        int id = scannerPromptInt("ID do ambiente: ");
        String Nome = scannerPrompt("Nome do ambiente: ");
        int Numero = scannerPromptInt("Numero do ambiente: ");
        System.out.println(controller.atualizarAmbiente(id, Nome, Numero));
    }
    private void remover(){
         int id = scannerPromptInt("ID do ambiente: ");
        System.out.println(controller.removerAmbiente(id));
    }
    public void listar() {
        for (Ambiente a : controller.listarAmbiente()) {
            System.out.printf("ID: %d | Nome: %d | Número: %d\n",
                    a.getId(), a.getNome(), a.getNumero());
        }
    }
    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }
    private String scannerPrompt(String msg) {
        System.out.print(msg);
        return (scanner.nextLine());
    }
}
