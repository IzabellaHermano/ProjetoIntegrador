package com.senai.projeto_catraca.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.senai.projeto_catraca.model.usuario.aluno.Ocorrencia;
import com.senai.projeto_catraca.model.usuario.aluno.OcorrenciaDAO;

public class OcorrenciaController {
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // Métodos CRUD para gerenciar ocorrências via JSON
    public boolean adicionarOcorrencia(Ocorrencia ocorrencia) {
        try {
            ocorrenciaDAO.adicionar(ocorrencia);
            System.out.println("Ocorrência adicionada ao JSON: ID " + ocorrencia.getId());
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao adicionar ocorrência ao JSON: " + e.getMessage());
            return false;
        }
    }

    public List<Ocorrencia> listarOcorrencias() {
        return ocorrenciaDAO.listar();
    }

    public Ocorrencia buscarOcorrenciaPorId(int id) {
        return ocorrenciaDAO.buscarPorId(id);
    }

    public boolean atualizarOcorrencia(Ocorrencia ocorrencia) {
        boolean sucesso = ocorrenciaDAO.atualizar(ocorrencia);
        
        if (sucesso) {
            System.out.println("Ocorrência atualizada no JSON: ID " + ocorrencia.getId());
        } else {
            System.err.println("Falha ao atualizar ocorrência: ID " + ocorrencia.getId());
        }
        
        return sucesso;
    }

    public boolean removerOcorrencia(int id) {
        boolean sucesso = ocorrenciaDAO.deletar(id);
        
        if (sucesso) {
            System.out.println("Ocorrência removida do JSON: ID " + id);
        } else {
            System.err.println("Falha ao remover ocorrência: ID " + id);
        }
        
        return sucesso;
    }

    // Método para criar uma nova ocorrência
    public boolean criarOcorrencia(int alunoId, int justificativaId) {
        try {
            int novoId = gerarProximoId();
            String dataHora = LocalDateTime.now().format(FORMATTER);
            
            Ocorrencia novaOcorrencia = new Ocorrencia(novoId, alunoId, justificativaId, dataHora);
            
            return adicionarOcorrencia(novaOcorrencia);
        } catch (Exception e) {
            System.err.println("Erro ao criar ocorrência: " + e.getMessage());
            return false;
        }
    }
       private int gerarProximoId() {
        List<Ocorrencia> ocorrencias = listarOcorrencias();
        return ocorrencias.stream()
                .mapToInt(Ocorrencia::getId)
                .max()
                .orElse(0) + 1;
    }


    // Método para criar ocorrência de atraso
    public boolean criarOcorrenciaAtraso(int alunoId) {
        return criarOcorrencia(alunoId, 1); // Assumindo que 1 = justificativa de atraso
    }

    // Método para criar ocorrência de falta
    //aqui eu ainda preciso ver com o andre
    public boolean criarOcorrenciaFalta(int alunoId) {
        return criarOcorrencia(alunoId, 2); // Assumindo que 2 = justificativa de falta
    }

    // Método para buscar ocorrências por aluno
    public List<Ocorrencia> buscarOcorrenciasPorAluno(int alunoId) {
        return ocorrenciaDAO.listar().stream()
                .filter(o -> o.getAlunoID() == alunoId)
                .toList();
    }

    // Método para buscar ocorrências por tipo de justificativa
    public List<Ocorrencia> buscarOcorrenciasPorJustificativa(int justificativaId) {
        return ocorrenciaDAO.listar().stream()
                .filter(o -> o.getJustificativaID() == justificativaId)
                .toList();

}
}