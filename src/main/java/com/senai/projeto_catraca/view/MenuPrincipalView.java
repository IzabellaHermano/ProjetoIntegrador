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
        OcorrenciaController ocorrenciaController = new OcorrenciaController();

        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de Aluno:                                  |
                                        |           1- Gerenciar Justificativa                  |
                                        |           2- Buscar Cartão RFID                       |
                                        |           3- Listar Ocorrências                       |
                                        |           4- Logout                                   |
                                        |           5- Sair                                     |
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
                case 3:
                    int id = scannerPromptInt("|ID:");
                    ocorrenciaController.buscarOcorrenciasPorAluno(id);
                case 4:
                    logar();
                    break;
                case 5:
                    System.out.println("Fim de programa...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcaoMenu != 5);


    }

    private static void menuProfessor(Professor professor) {
        JustificativaView justificativaView = new JustificativaView();
        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de Professor:                              |
                                        |           1- Receber Notificação                      |
                                        |           2- Verificar Justificativas                 |
                                        |           3- Logout                                   |
                                        |           4- Sair                                     |
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
                    justificativaView.exibirNaoAprovadasProfessor();
                    break;
                case 3:
                    WebSocketClienteConsole.desconectar();
                    logar();
                    break;
                case 4:
                    System.out.println("Fim de Programa...");
                    WebSocketClienteConsole.desconectar();
                    break;
            }
        } while (opcaoMenu != 4);


    }

    private static void menuCoordenador(Coordenador coordenador) {
        CoordenadorView coordenadorView = new CoordenadorView();
        AlunoView alunoView = new AlunoView();
        AqvView aqvView = new AqvView();
        OcorrenciaView ocorrenciaView = new OcorrenciaView();
        JustificativaController justificativaController = new JustificativaController();
        ProfessorView professorView = new ProfessorView();
        CursoView cursoView = new CursoView();
        TurmaView turmaView = new TurmaView();
        HorarioView horarioView = new HorarioView();

        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de Coordenador:                            |
                                        |           1- Gerenciar Aluno                          |
                                        |           2- Gerenciar AQV                            |
                                        |           3- Gerenciar Coordenadores                  |
                                        |           4- Gerenciar Professores                    |
                                        |           5- Gerenciar Cursos                         |
                                        |           6- Gerenciar Turmas                         |
                                        |           7- Gerenciar Horarios                       |
                                        |           8- Analisar Ocorrências                     |
                                        |           9- Listar Justificativas                    |
                                        |           10- Logout                                  |
                                        |           11- Sair                                    |
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
                    professorView.menu();
                    break;
                case 5:
                    cursoView.menu();
                    break;
                case 6:
                    turmaView.menu();
                    break;
                case 7:
                    horarioView.menu();
                    break;
                case 8:
                    ocorrenciaView.executarMenu();
                    break;
                case 9:
                    justificativaController.listarJustificativa();
                    break;
                case 10:
                    logar();
                    break;
                case 11:
                    System.out.println("Fim do Programa!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcaoMenu != 11);
    }

    private static void menuAqv(AQV aqv) {
        JustificativaView justificativaView = new JustificativaView();
        JustificativaController justificativaController = new JustificativaController();
        OcorrenciaController ocorrenciaController = new OcorrenciaController();
        AlunoView alunoView = new AlunoView();
        int opcaoMenu;
        do {
            String menu = """
                                        _________________________________________________________
                                        |   Bem-Vindo ao SENAI - Anchieta:                      |
                                        |       Menu de AQV:                                    |
                                        |           1- Analisar Justificativas                  |
                                        |           2- Listar Ocorrencias                       |
                                        |           3- Buscar Ocorrencia por Aluno              |
                                        |           4- Buscar Ocorrencia por Justificativa      |
                                        |           5- Alunos                                   |
                                        |           6- Logout                                   |
                                        |           7- Sair                                     |
                                        |_______________________________________________________|
                    """;
            System.out.println(menu);
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoMenu){
                case 1:
                    int opJust;
                    do{
                        String menuJust = """
                                | 1- listar Justificativas
                                | 2- Buscar Justificativa por ID
                                | 3- Analisar Justificativas
                                | 4- Voltar
                                """;
                        System.out.println(menuJust);
                        opJust = scanner.nextInt();
                        scanner.nextLine();
                        switch (opJust){
                            case 1:
                                justificativaView.exibir();
                                break;
                            case 2:
                                System.out.println("Digite o ID que deseja Buscar:");
                                int id = scannerPromptInt("|ID:");
                                System.out.println(justificativaController.buscarPorId(id));
                                break;
                            case 3:
                                justificativaView.exibirNaoAprovadas();
                                break;
                            case 4:
                                menuAqv(aqv);
                                break;
                            default:
                                System.out.println("Opcão Inválida");
                        }
                    }while (opJust != 4);
                    break;
                case 2:
                    ocorrenciaController.listarOcorrencias();
                    break;
                case 3:
                    int id = scannerPromptInt("|ID:");
                    System.out.println(ocorrenciaController.buscarOcorrenciasPorAluno(id));
                    break;
                case 4:
                    int idJ = scannerPromptInt("|ID:");
                    System.out.println(ocorrenciaController.buscarOcorrenciasPorJustificativa(idJ));
                    break;
                case 5:
                    alunoView.menu();
                    break;
                case 6:
                    logar();
                    break;
                case 7:
                    System.out.println("Fim do programa...");
                    break;
                default:
                    System.out.println("Opção Invalida!");
            }
        }while (opcaoMenu !=7);
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
