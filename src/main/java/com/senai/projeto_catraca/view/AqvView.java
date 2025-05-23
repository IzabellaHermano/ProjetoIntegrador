package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.model.dao.json.AQVDAO;
import com.senai.projeto_catraca.model.usuario.AQV;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AqvView {
    public static void main(String[] args) {
        System.out.printf("Bem vindo");
        Scanner scanner = new Scanner(System.in);
        AQVDAO aqvDAO = new AQVDAO();
        executarMenu("""
                    ===== MENU AQV =====
                    1. Cadastrar AQV
                    2. Listar AQVs
                    3. Remover AQV
                    4. Atualizar AQV
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> {
                            System.out.println("Digite o nome: ");
                            String nome = scanner.nextLine();
                            System.out.println("Digite uma senha: ");
                            String senha = scanner.nextLine();
                            System.out.println("Digite um CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.println("Digite um endereço: ");
                            String endereco = scanner.nextLine();
                            System.out.println("Digite um telefone: ");
                            String telefone = scanner.nextLine();

                            AQV novoAQV = new AQV(nome, senha, cpf, 0, endereco, telefone);
                            aqvDAO.inserir(novoAQV);
                            System.out.println("AQV cadastrado com sucesso!");
                            System.out.println("Nome: " + novoAQV.getNome());
                            System.out.println("Endereço: " + endereco);
                        }
                        case "2" -> {
                            List<AQV> listaAQVs = aqvDAO.listarTodos();
                            if (listaAQVs.isEmpty()) {
                                System.out.println("Nenhum AQV cadastrado.");
                            } else {
                                System.out.println("Lista de AQVs:");
                                for (AQV aqv : listaAQVs) {
                                    System.out.println("ID: " + aqv.getId() + ", Nome: " + aqv.getNome());
                                }
                            }
                        }
                        case "3" -> {
                            System.out.println("Remover");
                            System.out.println("Qual o ID do AQV que deseja remover?");
                            int id = scanner.nextInt();
                            aqvDAO.remover(id);
                            System.out.println("Removido com sucesso");
                            scanner.nextLine(); // limpa o buffer
                        }
                        case "4" -> {
                            System.out.println("Atualizar");
                            System.out.println("Qual o ID do AQV que deseja atualizar?");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            Optional<AQV> aqvOpt = aqvDAO.buscarPorId(id);
                            if (aqvOpt.isPresent()) {
                                AQV aqv = aqvOpt.get();
                                System.out.println("Digite o novo nome (atual: " + aqv.getNome() + "):");
                                String nome = scanner.nextLine();
                                System.out.println("Digite a nova senha (atual: " + aqv.getSenha() + "):");
                                String senha = scanner.nextLine();
                                System.out.println("Digite o novo CPF (atual: " + aqv.getCPF() + "):");
                                String cpf = scanner.nextLine();
                                System.out.println("Digite o novo endereço (atual: " + aqv.getEndereco() + "):");
                                String endereco = scanner.nextLine();
                                System.out.println("Digite o novo telefone (atual: " + aqv.getTelefone() + "):");
                                String telefone = scanner.nextLine();

                                aqv.setNome(nome);
                                aqv.setSenha(senha);
                                aqv.setCPF(cpf);
                                aqv.setEndereco(endereco);
                                aqv.setTelefone(telefone);

                                aqvDAO.atualizar(aqv);
                                System.out.println("AQV atualizado com sucesso!");
                            } else {
                                System.out.println("AQV com ID " + id + " não encontrado.");
                            }
                        }
                        default -> {
                            if (!opcao.equals("0")) {
                                System.out.println("Opção inválida!");
                            }
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
