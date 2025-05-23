package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.TurmaController;
import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;

import java.util.Scanner;

public class TurmaView {
    private final Scanner scanner = new Scanner(System.in);
    private final TurmaController controller = new TurmaController();
    private final SubTurma subTurma = new SubTurma(0, )
    public static void main(String[] args) {
        TurmaView view = new TurmaView();
        view.menu();
    }
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
     String alunos = scannerPrompt("Quantidade de alunos: ");
     String DataInicio = scannerPrompt("Data de inicio: ");
     int QntSemestres = scannerPromptInt("Quantidades de Semestres: ");
     String HorarioEntrada = scannerPrompt("O Horario de entrada: ");
     String Periodo = scannerPrompt("O periodo entre as aulas: ");

        System.out.println(controller.cadastrarTurma(Nome, Sigla, DataInicio, QntSemestres, HorarioEntrada, Periodo, new SubTurma(0, alunos)));
    }

    private void atualizar(){
        int idTurma = scannerPromptInt("ID da turma: ");
        String Nome = scannerPrompt("Nome da turma: ");
        String Sigla = scannerPrompt("Sigla da turma: ");
        String alunos = scannerPrompt("Quantidade de alunos: ");
        String DataInicio = scannerPrompt("Data de inicio: ");
        int QntSemestres = scannerPromptInt("Quantidades de Semestres: ");
        String HorarioEntrada = scannerPrompt("O Horario de entrada: ");
        String Periodo = scannerPrompt("O periodo entre as aulas: ");
        System.out.println(controller.atualizarTurma(idTurma, Nome, Sigla, DataInicio, QntSemestres, HorarioEntrada, Periodo, new SubTurma(0, alunos)));
    }


    private void remover(){
        int idTurma = scannerPromptInt("ID da turma: ");
        System.out.println(controller.removerHorario(idTurma));
    }
    public void listar(){
        for (Turmas t : controller.listarTurmas()){
            System.out.printf("ID: %d | Nome: %s | Sigla %s | Data de inicio %d " +
                            "| Quantidade de Semestres %d | Horario de entrada %d | Periodo %d",
                    t.getId(), t.getNome(), t.getSigla(), t.getDataInicio(), t.getQntSemestre(), t.getHorarioEntrada(), t.getPeriodo());
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
