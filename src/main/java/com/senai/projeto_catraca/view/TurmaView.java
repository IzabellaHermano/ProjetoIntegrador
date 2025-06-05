package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.TurmaController;
import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TurmaView {
    private final Scanner scanner = new Scanner(System.in);
    private final TurmaController controller = new TurmaController();
    public static void main(String[] args) {
        TurmaView view = new TurmaView();
        view.menu();
    }
    public void menu(){
      String opcao;
      String menuTurma = """
                 \n1 - Cadastrar Turma
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
        String nome = scannerPrompt("Nome da turma: ");
        String sigla = scannerPrompt("Sigla da turma: ");
        String dataInicio = scannerPrompt("Data de inicio: ");
        int qntSemestres = scannerPromptInt("Quantidades de Semestres: ");
        String horarioEntrada = scannerPrompt("O Horario de entrada: ");
        String periodo = scannerPrompt("O periodo entre as aulas: ");
//        List<String> alunos = new ArrayList<>();
//        String aluno;
//        do {
//            aluno = scannerPrompt("Digite o nome do aluno (ou 'fim' para encerrar): ");
//            if (!aluno.equalsIgnoreCase("fim")) {
//                alunos.add(aluno);
//            }
//        } while (!aluno.equalsIgnoreCase("fim"));
//        SubTurma subTurma = new SubTurma(0, alunos);
//        List<SubTurma> listaSubTurmas = new ArrayList<>();
//        listaSubTurmas.add(subTurma);
        //codigo para a lista de SubTurma.
        System.out.println(controller.cadastrarTurma(nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo));

    }


    private void atualizar(){
            int id = scannerPromptInt("ID da turma: ");
            String nome = scannerPrompt("Nome da turma: ");
            String sigla = scannerPrompt("Sigla da turma: ");
            String dataInicio = scannerPrompt("Data de inicio: ");
            int qntSemestres = scannerPromptInt("Quantidades de Semestres: ");
            String horarioEntrada = scannerPrompt("O Horario de entrada: ");
            String periodo = scannerPrompt("O periodo entre as aulas: ");
            ;
        System.out.println(controller.atualizarTurma(id,nome, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, id));

    }



        private void remover(){
        int idTurma = scannerPromptInt("ID da turma: ");
        System.out.println(controller.removerTurma(idTurma));
    }
    public void listar(){
        for (Turmas t : controller.listarTurmas()){
            System.out.printf("ID: %d | Nome: %s | Sigla: %s | Data de inicio: %s | Quantidade de Semestres: %d | Horario de entrada: %s | Periodo: %s\n",
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
