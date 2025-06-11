package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.AlunoController;
import com.senai.projeto_catraca.controller.JustificativaController;
import com.senai.projeto_catraca.controller.OcorrenciaController;
import com.senai.projeto_catraca.model.usuario.AQV;
import com.senai.projeto_catraca.model.usuario.Coordenador;
import com.senai.projeto_catraca.model.usuario.Professor;
import com.senai.projeto_catraca.model.usuario.Usuario;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;
import com.senai.projeto_catraca.model.usuario.aluno.AlunoDAO;
import com.senai.projeto_catraca.model.usuario.aluno.Ocorrencia;
import com.senai.projeto_catraca.websocket.WebSocketClienteConsole;

import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipalView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        logar();
    }

    public static void logar() {
        Optional<Usuario> usuarioLogin = new LoginView().exibirLogin();
        usuarioLogin.ifPresent(MenuPrincipalView::redirecionarMenu);
    }

    private static void redirecionarMenu(Usuario usuario) {
        switch (usuario.getTipo()) {
            case "Aluno" -> menuAluno((Aluno) usuario);
            case "Professor" -> menuProfessor((Professor) usuario);
            case "Coordenador" -> menuCoordenador((Coordenador) usuario);
            case "Aqv" -> menuAqv((AQV) usuario);
            default -> {
                System.out.println("Usuário Inválido!");
                logar();
            }
        }
    }

    private static void menuAluno(Aluno aluno) {
        JustificativaView justificativaView = new JustificativaView();
        AlunoController alunoController = new AlunoController();

        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de Aluno:                                  |
                                        |           1- Gerenciar Justificativa                  |
                                        |           2- Buscar Cartão RFID                       |
                                        |           3- Solicitar Entrada                        |
                                        |           4- Solicitar Saida                          |
                                        |           5- Listar Ocorrências                       |
                                        |           6- Logout                                   |
                                        |           7- Sair                                     |
                                        |_______________________________________________________|
                    """;
            System.out.println(menu);
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoMenu) {
                case 1:
                    justificativaView.menu();
                    break;
                case 2:
                    String nome = scannerPrompt("|Nome: ");
                    System.out.println(alunoController.buscarRFID(nome));
                    break;
                case 6:
                    logar();
                case 7:
                    System.out.println("Fim de programa...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcaoMenu != 7);


    }

    private static void menuProfessor(Professor professor) {
        HorarioView horarioView = new HorarioView();
        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de Professor:                              |
                                        |           1- Receber Notificação                      |
                                        |           2- Logout                                   |
                                        |           3- Sair                                     |
                                        |_______________________________________________________|
                    """;
            System.out.println(menu);
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoMenu) {
                case 1:
                    WebSocketClienteConsole.conectar();
                    break;
                case 2:
                    WebSocketClienteConsole.desconectar();
                    logar();
                    break;
                case 3:
                    System.out.println("Fim de Programa...");
                    WebSocketClienteConsole.desconectar();
                    break;
            }
        } while (opcaoMenu != 2);


    }

    private static void menuCoordenador(Coordenador coordenador) {
        CoordenadorView coordenadorView = new CoordenadorView();
        AlunoView alunoView = new AlunoView();
        AqvView aqvView = new AqvView();
        OcorrenciaController ocorrenciaController = new OcorrenciaController();
        JustificativaController justificativaController = new JustificativaController();
        ProfessorView professorView = new ProfessorView();

        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de Coordenador:                            |
                                        |           1- Gerenciar Aluno                          |
                                        |           2- Gerenciar AQV                            |
                                        |           3- Gerenciar Coordenadore                   |
                                        |           4- Analisar Ocorrências                     |
                                        |           5- Listar Justificativas                    |
                                        |           6- Logout                                   |
                                        |           7- Sair                                     |
                                        |_______________________________________________________|
                    """;
            System.out.println(menu);
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoMenu) {
                case 1:
                    alunoView.menu();
                    break;
                case 2:
                    aqvView.menu();
                    break;
                case 3:
                    coordenadorView.menu();
                    break;
                case 4:

                    break;
                case 5:
                    break;
                case 6:
                    logar();
                    break;
                case 7:
                    System.out.println("Fim do Programa...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcaoMenu != 7);

    }

    private static void menuAqv(AQV aqv) {

    }

    private static String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }
}
