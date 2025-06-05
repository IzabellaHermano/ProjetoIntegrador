package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.AmbienteController;
import com.senai.projeto_catraca.controller.HorarioController;
import com.senai.projeto_catraca.model.turma.horario.Ambiente;
import com.senai.projeto_catraca.model.turma.horario.HorarioBase;

import java.time.LocalTime;
import java.util.Scanner;

public class HorarioView {
    private final Scanner scanner = new Scanner(System.in);
    private final HorarioController controller = new HorarioController();

    public static void main(String[] args) {
      HorarioView view = new HorarioView();
      view.menu();

    }
    public void menu() {
        String opcao;
        String menuTurma = """
                   1 - Cadastrar Horário
                   2 - Atualizar Horário
                   3 - Remover Horário
                   4 - Listar Horário
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
        int professor = scannerPromptInt("ID do professor: ");
        int turma = scannerPromptInt("ID da turma: ");
        LocalTime horario = LocalTime.parse(scannerPrompt("Horario da turma: "));
        System.out.println(controller.cadastrarHorario(professor, turma, horario));
    }
    private void atualizar(){
        int id = scannerPromptInt("ID do horario: ");
        int professor = scannerPromptInt("ID do professor: ");
        int turma = scannerPromptInt("ID da turma");
        LocalTime horario = LocalTime.parse(scannerPrompt("Horario da turma: "));
        System.out.println(controller.atualizarHorario(id, professor, turma, horario));
    }
    private void remover(){
        int id = scannerPromptInt("ID do ambiente: ");
        System.out.println(controller.removerHorario(id));
    }
    public void listar() {
        for (HorarioBase h : controller.listarHorario()) {
            System.out.printf("ID: %d | ID do Professor: %d | ID do Aluno: %d | Horario: %s%n",
                    h.getId(), h.getIdProfessor(), h.getIdTurma(), h.getHorario());
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
