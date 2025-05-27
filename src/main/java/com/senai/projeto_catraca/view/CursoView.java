package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.model.curso.Curso;
import com.senai.projeto_catraca.model.curso.CursoDAO;
import com.senai.projeto_catraca.model.curso.UnidadeCurricular;
import com.senai.projeto_catraca.model.curso.UnidadeCurricularDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class CursoView {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int opcao;
            do{
                String menu = """
                _______________________________________________
                |      1. Adicionar cursos                    |
                |      2. Listar cursos                       |
                |      3. Remover cursos                      |
                |      4. Adicionar unidade curricular        |
                |      5. Listar unidades curiculares         |
                |      6. Remover unidade currricular         |     
                |      0. Sair                                |
                |_____________________________________________|
               """;
                opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1:
                        System.out.print("ID do curso: "); 
                        int idCurso = Integer.parseInt(scanner.nextLine()); 
                        
                        System.out.print("Nome do curso: ");
                        String nomeCurso = scanner.nextLine();
                        
                        Curso novoCurso = new Curso(nomeCurso);
                        CursoDAO.adicionarCurso(novoCurso);
                        
                        System.out.println("Curso adicionado com sucesso.");
                        break;
                        
                    case 2:
                        System.out.println("\n--- Lista de Cursos ---");
                        for (Curso c : CursoDAO.listarCursos()) {
                            System.out.println("-" + c.getNome());
                        }
                        break;
                        
                    case 3:
                        System.out.print("Id do curso a remover: ");
                        int idRemover = Integer.parseInt(scanner.nextLine());
                        if (CursoDAO.removerCurso(Integer.parseInt(String.valueOf(idRemover)))) {
                            System.out.println("Curso removido com sucesso");
                        } else {
                            System.out.println("Curso não encontrado");
                        }
                        break;
                        
                    case 4:
                        System.out.print("Nome da unidade curricular: ");
                        String nomeUC = scanner.nextLine();

                        System.out.print("Id do curso ao qual pertence: ");
                        idCurso = Integer.parseInt(scanner.nextLine());
                        
                       UnidadeCurricular novaUC = new UnidadeCurricular(nomeUC, idCurso);
                       UnidadeCurricularDAO.adicionarUC(novaUC);
                        System.out.println("Unidade Curricular adicionada com sucesso!" ); 
                        break;

                    case 5:
                        System.out.println("\n--- Lista de Unidades Curriculares ---");
                        ArrayList<UnidadeCurricular> ucs = UnidadeCurricularDAO.listarUCs();
                        if (ucs.isEmpty()) {
                            System.out.println("Nenhuma unidade curricular cadastrada. ");
                        }else{
                        for (UnidadeCurricular uc : UnidadeCurricularDAO.listarUCs()) {
                            System.out.println("- " + uc.getNome());
                        }
                    }
                        break;
                        
                    case 6:
                        System.out.print("Nome da unidade curricular a remover: ");
                        String ucRemover = scanner.nextLine();
                        if (UnidadeCurricularDAO.removerUC(Integer.parseInt(ucRemover))) {
                            System.out.println("UC removida com sucesso.");
                        } else {
                            System.out.println("UC não encontrada.");
                        }
                        break;

                    case 7:
                        System.out.print("Digite o ID do curso para listar as unidades curriculares: ");
                        int buscarCursoPorId = Integer.parseInt(scanner.nextLine()); 
                        ArrayList<UnidadeCurricular> ucsDoCurso = UnidadeCurricularDAO.listarUCsPorCurso(buscarCursoPorId);
                        if (ucsDoCurso.isEmpty()) {
                            System.out.println("Nenhuma unidade curricular encontrada para esse curso.");
                        }else{
                            System.out.println("\n--- Unidades Curriculares do Curso ID " + buscarCursoPorId + "----");
                            for (UnidadeCurricular uc : ucsDoCurso){
                                System.out.println("-" + uc.getNome()); 
                            }
                        }
                        break; 
                    
                    case 0:
                        System.out.println("Encerrando testes...");
                        break;
                        
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
            scanner.close();
        }
    }



