package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.model.dao.json.ProfessorDAO;
import com.senai.projeto_catraca.model.usuario.Professor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProfessorView {
    public static void main(String[] args) {
        System.out.printf("Bem vindo");
        Scanner scanner = new Scanner(System.in);
        ProfessorDAO professorDAO = new ProfessorDAO();
        executarMenu("""
                    ===== MENU PROFESSOR =====
                    1. Cadastrar professor
                    2. Listar Professor
                    3. Remover Professor
                    4. Atualizar Professor
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> {
                            System.out.println("Digite o nome: ");
                            String nome = scanner.nextLine();
                            System.out.println("Digite uma senha: ");
                            String senha = scanner.nextLine();
                            System.out.println("Digite uma cpf: ");
                            String cpf = scanner.nextLine();
                            System.out.println("Digite um endereço: ");
                            String endereco = scanner.nextLine();
                            System.out.println("Digite um disciplina: ");
                            String disciplina = scanner.nextLine();
                            System.out.println("Digite uma telefone: ");
                            String telefone = scanner.nextLine();
                            Professor novoProfessor = new Professor(nome,senha,cpf,0,endereco,telefone,disciplina);
                            professorDAO.inserir(novoProfessor);
                            System.out.println("Professor cadastrado com sucesso!");
                            System.out.println("Nome: " + novoProfessor.getNome());
                            System.out.println("Disciplina: " + novoProfessor.getDisciplina());
                            System.out.println("Endereço: " + endereco);
                        }
                        case "2" -> {
                            List<Professor> listaProfessores = professorDAO.listarTodos();

                            if (listaProfessores.isEmpty()) {
                                System.out.println("Nenhum professor cadastrado.");
                            } else {
                                System.out.println("Lista de Professores:");
                                for (Professor professor : listaProfessores) {
                                    System.out.println("ID: " + professor.getId() + ", Nome: " + professor.getNome() + ", Disciplina: " + professor.getDisciplina());
                                }
                            }
                        }
                        case "3" -> {
                            System.out.println("Remover");
                            System.out.println("Qual o id do professor que deseja remover");
                            int id = scanner.nextInt();
                            professorDAO.remover(id);
                            System.out.println("Removido com sucesso");
                        }
                        case "4" -> {
                            System.out.println("Atualizar");
                            System.out.println("Qual o ID do professor que deseja atualizar?");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            Optional<Professor> professorOpt = professorDAO.buscarPorId(id);
                            if (professorOpt.isPresent()) {
                                Professor professor = professorOpt.get();
                                System.out.println("Digite o novo nome (atual: " + professor.getNome() + "):");
                                String nome = scanner.nextLine();
                                System.out.println("Digite a nova senha (atual: " + professor.getSenha() + "):");
                                String senha = scanner.nextLine();
                                System.out.println("Digite o novo CPF (atual: " + professor.getCPF() + "):");
                                String CPF = scanner.nextLine();
                                System.out.println("Digite o novo endereço (atual: " + professor.getEndereco() + "):");
                                String endereco = scanner.nextLine();
                                System.out.println("Digite o novo telefone (atual: " + professor.getTelefone() + "):");
                                String telefone = scanner.nextLine();
                                System.out.println("Digite a nova disciplina (atual: " + professor.getDisciplina() + "):");
                                String disciplina = scanner.nextLine();
                                professor.setNome(nome);
                                professor.setSenha(senha);
                                professor.setCPF(CPF);
                                professor.setEndereco(endereco);
                                professor.setTelefone(telefone);
                                professor.setDisciplina(disciplina);
                                professorDAO.atualizar(professor);
                                System.out.println("Professor atualizado com sucesso!");
                            } else {
                                System.out.println("Professor com ID " + id + " não encontrado.");
                            }
                        }

                        default -> {
                            System.out.println("Opção inválida!");
                        }
                    }
                });
    }
private static void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
    String opcao;
    Scanner scanner = new Scanner(System.in);
    do {
        System.out.print(titulo);
        opcao = scanner.nextLine();
        acoes.accept(opcao);
    } while (!opcao.equals("0"));
}
}
