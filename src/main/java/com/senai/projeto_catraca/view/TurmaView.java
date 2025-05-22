package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.TurmaController;

import java.util.Scanner;

public class TurmaView {
    private final Scanner scanner = new Scanner(System.in);
    private final TurmaController controller = new TurmaController();

    public void menu(){
      String opcao;
      String menuTurma = """
                 1 - Cadastrar Turma
                 2 - Lista Turma
                 3 - Atualizar Turma
                 4 - Remover Turma
                 0 - Sair.
              """;
do {
    System.out.println(menuTurma);
    opcao = scanner.nextLine();

    switch (opcao) {
        case "1" -> cadastrar();
        case "2" -> listar();
        case "3" -> atualizar();
        case "4" -> remover();
        case "0" -> System.out.println("Ate mais");
        default -> System.out.println("Opção invalida");
    }
      } while (!opcao.equals("0"));
    }
    private void cadastrar(){
     String Nome = scannerPrompt("Nome da turma: ");
     String Sigla = scannerPrompt("Sigla da turma: ");
     int Datainicio = scannerPromptInt("Data de inicio: ");
     int QntSemestres = scannerPromptInt("Quantidades de Semestres: ");
     int HorarioEntrada = scannerPromptInt("O Horario de entrada: ");
     int Periodo = scannerPromptInt("O periodo entre as aulas: ");
        System.out.println(controller.cadastrarTurma(Nome, Sigla, DataInicio, QntSemestres, HorarioEntrada, Periodo));
    }

    private void atualizar(){
        int idTurma = scannerPromptInt("ID da turma: ");
        String Sigla = scannerPrompt("Sigla da turma: ");
        int Datainicio = scannerPromptInt("Data de inicio: ");
        int QntSemestres = scannerPromptInt("Quantidades de Semestres: ");
        int HorarioEntrada = scannerPromptInt("O Horario de entrada: ");
        int Periodo = scannerPromptInt("O periodo entre as aulas: ");
        System.out.println(controller.atualizarTurma(idTurma, Nome, Sigla, DataInicio, QntSemestres, HorarioEntrada, Periodo));
    }

    private void remover(){
        int idTurma = scannerPromptInt("ID da turma: ");
        System.out.println(controller.removerHorario());
    }
    public void listar(){
        for (Turma t : controller.listarTurmas()){
            System.out.printf("ID: %d | Nome: %s | Sigla %s | Data de inicio %d " +
                            "| Quantidade de Semestres %d | Horario de entrada %d | Periodo %d",
                    t.getId(), t.getNome(), t.getSigla(), t.DataInicio(), t.QntSemestres(), t.HorarioEntrada(), t.Periodo());
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
