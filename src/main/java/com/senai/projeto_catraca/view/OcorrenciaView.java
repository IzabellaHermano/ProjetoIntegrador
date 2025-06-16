package com.senai.projeto_catraca.view;

import java.util.List;
import java.util.Scanner;

import com.senai.projeto_catraca.controller.JustificativaController;
import com.senai.projeto_catraca.controller.OcorrenciaController;
import com.senai.projeto_catraca.model.usuario.aluno.Ocorrencia;

public class OcorrenciaView {
    private final OcorrenciaController ocorrenciaController = new OcorrenciaController();
    private final JustificativaController justificativaController = new JustificativaController();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        OcorrenciaView view = new OcorrenciaView();
        view.executarMenu();
    }

    public void executarMenu() {
        executarMenu("""
                ===== MENU OCORRÊNCIA =====
                1. Adicionar Ocorrência
                2. Listar Ocorrências
                3. Atualizar Ocorrência
                4. Remover Ocorrência
                5. Gerenciar Ocorrências Pendentes
                6. Gerenciar Status das Ocorrências
                7. Listar por Status
                0. Sair
                """, opcao -> {
            switch (opcao) { // Corrigido: era "opcão"
                case "1" -> adicionarOcorrencia();
                case "2" -> listarOcorrencias();
                case "3" -> atualizarOcorrencia();
                case "4" -> removerOcorrencia();
                case "5" -> exibirOcorrenciasPendentes();
                case "6" -> gerenciarStatusOcorrencias();
                case "7" -> listarPorStatus();
                case "0" -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    private void adicionarOcorrencia() {
        try {
            System.out.println("Digite o ID da ocorrência:");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o ID do aluno:");
            int alunoId = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o ID da justificativa:");
            int justificativaId = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite a data e hora (ex: 29-05-2025 14:30):");
            String dataHora = scanner.nextLine();

            Ocorrencia ocorrencia = new Ocorrencia(id, alunoId, justificativaId, dataHora);

            if (ocorrenciaController.adicionarOcorrencia(ocorrencia)) {
                System.out.println("Ocorrência adicionada com sucesso!");
            } else {
                System.out.println("Erro ao adicionar ocorrência.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números para os IDs.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void gerenciarStatusOcorrencias() {
        List<Ocorrencia> lista = ocorrenciaController.listarOcorrencias();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma ocorrência cadastrada.");
            return;
        }

        System.out.println("===== GERENCIAR STATUS DAS OCORRÊNCIAS =====");
        System.out.printf("%-5s | %-8s | %-20s | %-30s%n", 
            "ID", "AlunoID", "DataHora", "Status Atual");
        System.out.println("-".repeat(70));
        
        for (Ocorrencia o : lista) {
            String status = (o.getStatus() != null && !o.getStatus().isEmpty()) 
                ? o.getStatus() : "Pendente";
                
            System.out.printf("%-5d | %-8d | %-20s | %-30s%n",
                    o.getId(), o.getAlunoID(), o.getDataHora(), status);
        }

        try {
            System.out.println("\nDigite o ID da ocorrência para alterar o status (0 para voltar):");
            int id = Integer.parseInt(scanner.nextLine());
            
            if (id == 0) return;
            
            Ocorrencia ocorrencia = ocorrenciaController.buscarOcorrenciaPorId(id);
            if (ocorrencia == null) {
                System.out.println("Ocorrência não encontrada.");
                return;
            }

            System.out.println("Status atual: " + 
                (ocorrencia.getStatus() != null ? ocorrencia.getStatus() : "Pendente"));
            
            String menuStatus = """
                    
                    ===== ALTERAR STATUS =====
                    1. Aprovada
                    2. Pendente
                    3. Recusada
                    4. Cancelar
                    """;
            
            System.out.println(menuStatus);
            System.out.print("Escolha o novo status: ");
            int opcaoStatus = Integer.parseInt(scanner.nextLine());
            
            String novoStatus;
            switch (opcaoStatus) {
                case 1:
                    novoStatus = "Aprovada";
                    break;
                case 2:
                    novoStatus = "Pendente";
                    break;
                case 3:
                    novoStatus = "Recusada";
                    break;
                case 4:
                    System.out.println("Operação cancelada.");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }
            
            if (ocorrenciaController.setStatusOcorrencia(id, novoStatus)) {
                System.out.println("Status da ocorrência alterado para: " + novoStatus);
            } else {
                System.out.println("Erro ao alterar status da ocorrência.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void listarPorStatus() {
        String menuFiltro = """
                
                ===== FILTRAR POR STATUS =====
                1. Aprovadas
                2. Pendentes
                3. Recusadas
                4. Todas
                """;
        
        System.out.println(menuFiltro);
        System.out.print("Escolha o filtro: ");
        
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            String statusFiltro;
            
            switch (opcao) {
                case 1:
                    statusFiltro = "Aprovada";
                    break;
                case 2:
                    statusFiltro = "Pendente";
                    break;
                case 3:
                    statusFiltro = "Recusada";
                    break;
                case 4:
                    listarOcorrencias();
                    return;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }
            
            List<Ocorrencia> lista = ocorrenciaController.listarOcorrencias();
            List<Ocorrencia> listaFiltrada = lista.stream()
                .filter(o -> {
                    String status = (o.getStatus() != null && !o.getStatus().isEmpty()) 
                        ? o.getStatus() : "Pendente";
                    return status.equalsIgnoreCase(statusFiltro);
                })
                .toList();
            
            if (listaFiltrada.isEmpty()) {
                System.out.println("Nenhuma ocorrência encontrada com status: " + statusFiltro);
            } else {
                System.out.println("===== OCORRÊNCIAS COM STATUS: " + statusFiltro.toUpperCase() + " =====");
                System.out.printf("%-5s | %-8s | %-15s | %-20s%n", 
                    "ID", "AlunoID", "JustificativaID", "DataHora");
                System.out.println("-".repeat(55));
                
                for (Ocorrencia o : listaFiltrada) {
                    System.out.printf("%-5d | %-8d | %-15d | %-20s%n",
                            o.getId(), o.getAlunoID(), o.getJustificativaID(), o.getDataHora());
                }
                System.out.println("Total: " + listaFiltrada.size() + " ocorrência(s)");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarOcorrencias() {
        List<Ocorrencia> lista = ocorrenciaController.listarOcorrencias();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma ocorrência cadastrada.");
        } else {
            System.out.println("===== LISTA DE TODAS AS OCORRÊNCIAS =====");
            System.out.printf("%-5s | %-8s | %-15s | %-20s | %-30s%n", 
                "ID", "AlunoID", "JustificativaID", "DataHora", "Status");
            System.out.println("-".repeat(85));
            
            for (Ocorrencia o : lista) {
                String status = (o.getStatus() != null && !o.getStatus().isEmpty()) 
                    ? o.getStatus() : "Pendente";
                    
                System.out.printf("%-5d | %-8d | %-15d | %-20s | %-30s%n",
                        o.getId(), o.getAlunoID(), o.getJustificativaID(), 
                        o.getDataHora(), status);
            }
        }
    }

    private void atualizarOcorrencia() {
        try {
            System.out.println("Digite o ID da ocorrência a ser atualizada:");
            int id = Integer.parseInt(scanner.nextLine());
            Ocorrencia ocorrencia = ocorrenciaController.buscarOcorrenciaPorId(id);

            if (ocorrencia != null) {
                System.out.println("Digite o novo ID do aluno (atual: " + ocorrencia.getAlunoID() + "):");
                int novoAlunoId = Integer.parseInt(scanner.nextLine());

                System.out.println("Digite o novo ID da justificativa (atual: " + ocorrencia.getJustificativaID() + "):");
                int novaJustificativaId = Integer.parseInt(scanner.nextLine());

                System.out.println("Digite a nova data e hora (atual: " + ocorrencia.getDataHora() + "):");
                String novaDataHora = scanner.nextLine();

                ocorrencia.setAlunoID(novoAlunoId);
                ocorrencia.setJustificativaID(novaJustificativaId);
                ocorrencia.setDataHora(novaDataHora);

                if (ocorrenciaController.atualizarOcorrencia(ocorrencia)) {
                    System.out.println("Ocorrência atualizada com sucesso.");
                } else {
                    System.out.println("Erro ao atualizar a ocorrência.");
                }
            } else {
                System.out.println("Ocorrência não encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números para os IDs.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void removerOcorrencia() {
        try {
            System.out.println("Digite o ID da ocorrência a ser removida:");
            int id = Integer.parseInt(scanner.nextLine());

            if (ocorrenciaController.removerOcorrencia(id)) {
                System.out.println("Ocorrência removida com sucesso.");
            } else {
                System.out.println("Ocorrência não encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números para o ID.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
        String opcao;
        do {
            System.out.println(titulo);
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();
            acoes.accept(opcao);
        } while (!opcao.equals("0"));
    }

    public void exibirOcorrenciasPendentes() {
        if (ocorrenciaController.listarOcorrencias().isEmpty()) {
            System.out.println("Não há Ocorrências cadastradas!!");
            return;
        }

        System.out.println("--- Ocorrências Pendentes ---");
        
        // Primeiro, coletar as ocorrências pendentes
        List<Ocorrencia> ocorrenciasPendentes = ocorrenciaController.listarOcorrencias().stream()
            .filter(o -> {
                String status = (o.getStatus() != null && !o.getStatus().isEmpty()) 
                    ? o.getStatus() : "Pendente";
                return status.equals("Pendente");
            })
            .toList();
        
        if (ocorrenciasPendentes.isEmpty()) {
            System.out.println("Não há ocorrências pendentes.");
            return;
        }

        // Exibir as ocorrências pendentes
        for (Ocorrencia o : ocorrenciasPendentes) {
            System.out.printf("ID: %d | AlunoID: %d | JustificativaID: %d | Data/Hora: %s | Status: Pendente%n",
                    o.getId(), o.getAlunoID(), o.getJustificativaID(), o.getDataHora());
        }

        System.out.println("Deseja alterar o status de alguma ocorrência? (s/n)");
        String resposta = scanner.nextLine();
        
        if (resposta.equalsIgnoreCase("s")) {
            try {
                System.out.println("Digite o ID da ocorrência para aprovar ou reprovar: ");
                int idOcorrencia = Integer.parseInt(scanner.nextLine());
                
                // Verificar se a ocorrência existe e está pendente
                Ocorrencia ocorrenciaEncontrada = ocorrenciasPendentes.stream()
                    .filter(o -> o.getId() == idOcorrencia)
                    .findFirst()
                    .orElse(null);
                
                if (ocorrenciaEncontrada == null) {
                    System.out.println("Ocorrência não encontrada ou não está pendente.");
                    return;
                }

                String novoStatus;
                String menu = """
                        1. Aprovar
                        2. Reprovar
                        """;
                System.out.println(menu);
                int escolha = Integer.parseInt(scanner.nextLine());
                
                switch (escolha) {
                    case 1:
                        novoStatus = "Aprovada";
                        if (ocorrenciaController.setStatusOcorrencia(idOcorrencia, novoStatus)) {
                            System.out.println("Ocorrência aprovada com sucesso.");
                        } else {
                            System.out.println("Erro ao aprovar ocorrência.");
                        }
                        break;
                        
                    case 2:
                        novoStatus = "Recusada";
                        if (ocorrenciaController.setStatusOcorrencia(idOcorrencia, novoStatus)) {
                            System.out.println("Ocorrência recusada com sucesso.");
                        } else {
                            System.out.println("Erro ao recusar ocorrência.");
                        }
                        break;
                        
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números para o ID e opção.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}