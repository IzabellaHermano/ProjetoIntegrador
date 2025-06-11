package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.TurmaController;
import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

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
        String sigla = scannerPrompt("Sigla da turma: ");
        String dataInicio = scannerPrompt("Data de inicio: ");
        int qntSemestres = scannerPromptInt("Quantidades de Semestres: ");
        String horarioEntrada = scannerPrompt("O Horario de entrada: ");
        String periodo = scannerPrompt("O periodo entre as aulas: ");

        List<Aluno> alunos = new ArrayList<>();
        String resposta;
        do {
            System.out.println("Cadastrando novo aluno:");

            String nomealuno = scannerPrompt("Nome: ");
            String senha = scannerPrompt("Senha: ");
            String cpf = scannerPrompt("CPF: ");
            int id = scannerPromptInt("ID do usuário: ");
            String endereco = scannerPrompt("Endereço: ");
            String telefone = scannerPrompt("Telefone: ");
            String idCartaoRfid = scannerPrompt("ID do Cartão RFID: ");
            int matricula = scannerPromptInt("Matrícula: ");

            Aluno aluno = new Aluno(nomealuno, senha, cpf, id, endereco, telefone, idCartaoRfid,
                    new ArrayList<>(), new ArrayList<>(), matricula);

            alunos.add(aluno);

            resposta = scannerPrompt("Deseja adicionar outro aluno? (sim/fim): ");
        } while (!resposta.equalsIgnoreCase("fim"));

        SubTurma subTurma = new SubTurma(0, alunos);
        List<SubTurma> listaSubTurmas = new ArrayList<>();
        listaSubTurmas.add(subTurma);

        //codigo para a lista de SubTurma.
        System.out.println(controller.cadastrarTurma( sigla, dataInicio, qntSemestres, horarioEntrada, periodo, listaSubTurmas));

    }


    private void atualizar(){
            int id = scannerPromptInt("ID da turma: ");
            String nome = scannerPrompt("Nome da turma: ");
            String sigla = scannerPrompt("Sigla da turma: ");
            String dataInicio = scannerPrompt("Data de inicio: ");
            int qntSemestres = scannerPromptInt("Quantidades de Semestres: ");
            String horarioEntrada = scannerPrompt("O Horario de entrada: ");
            String periodo = scannerPrompt("O periodo entre as aulas: ");

        List<Aluno> alunos = new ArrayList<>();
        String resposta;
        do {
            System.out.println("Atulizando aluno:");

            String nomealuno = scannerPrompt("Nome: ");
            String senha = scannerPrompt("Senha: ");
            String cpf = scannerPrompt("CPF: ");
            int idatualiza = scannerPromptInt("ID do usuário: ");
            String endereco = scannerPrompt("Endereço: ");
            String telefone = scannerPrompt("Telefone: ");
            String idCartaoRfid = scannerPrompt("ID do Cartão RFID: ");
            int matricula = scannerPromptInt("Matrícula: ");

            Aluno aluno = new Aluno(nomealuno, senha, cpf, idatualiza, endereco, telefone, idCartaoRfid,
                    new ArrayList<>(), new ArrayList<>(), matricula);

            alunos.add(aluno);

            resposta = scannerPrompt("Deseja adicionar outro aluno? (sim/fim): ");
        } while (!resposta.equalsIgnoreCase("fim"));

        SubTurma subTurma = new SubTurma(0, alunos);
        List<SubTurma> listaSubTurmas = new ArrayList<>();
        listaSubTurmas.add(subTurma);
            ;
        System.out.println(controller.atualizarTurma(id, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, listaSubTurmas));

    }



        private void remover(){
        int idTurma = scannerPromptInt("ID da turma: ");
        System.out.println(controller.removerTurma(idTurma));
    }
    public void listar(){
        for (Turmas t : controller.listarTurmas()){
            System.out.printf("ID: %d | Sigla: %s | Data de inicio: %s | Quantidade de Semestres: %d | Horario de entrada: %s | Periodo: %s\n",
                    t.getId(), t.getSigla(), t.getDataInicio(), t.getQntSemestre(), t.getHorarioEntrada(), t.getPeriodo());
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
