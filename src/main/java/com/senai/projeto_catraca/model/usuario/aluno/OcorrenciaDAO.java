package com.senai.projeto_catraca.model.usuario.aluno;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class OcorrenciaDAO {
    private static final String FILE_PATH = "ocorrencias.json";
    private static final Logger LOGGER = Logger.getLogger(OcorrenciaDAO.class.getName());
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type listType = new TypeToken<List<Ocorrencia>>() {}.getType();

    private List<Ocorrencia> lerOcorrencias() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            List<Ocorrencia> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Arquivo de ocorrências não encontrado. Criando nova lista.", e);
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao ler arquivo de ocorrências", e);
            return new ArrayList<>();
        }
    }

    private boolean salvarOcorrencias(List<Ocorrencia> lista) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(lista, writer);
            return true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar ocorrências no arquivo", e);
            return false;
        }
    }

    public boolean adicionar(Ocorrencia ocorrencia) {
        if (ocorrencia == null) {
            LOGGER.warning("Tentativa de adicionar ocorrência nula");
            return false;
        }
        
        List<Ocorrencia> lista = lerOcorrencias();
        
        // Verificar se já existe uma ocorrência com o mesmo ID
        boolean jaExiste = lista.stream().anyMatch(o -> o.getId() == ocorrencia.getId());
        if (jaExiste) {
            LOGGER.warning("Ocorrência com ID " + ocorrencia.getId() + " já existe");
            return false;
        }
        
        lista.add(ocorrencia);
        return salvarOcorrencias(lista);
    }

    public boolean atualizar(Ocorrencia ocorrenciaAtualizada) {
        if (ocorrenciaAtualizada == null) {
            LOGGER.warning("Tentativa de atualizar com ocorrência nula");
            return false;
        }
        
        List<Ocorrencia> lista = lerOcorrencias();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == ocorrenciaAtualizada.getId()) {
                lista.set(i, ocorrenciaAtualizada);
                return salvarOcorrencias(lista);
            }
        }
        LOGGER.warning("Ocorrência com ID " + ocorrenciaAtualizada.getId() + " não encontrada para atualização");
        return false;
    }

    public boolean deletar(int id) {
        List<Ocorrencia> lista = lerOcorrencias();
        boolean removido = lista.removeIf(o -> o.getId() == id);
        if (removido) {
            return salvarOcorrencias(lista);
        } else {
            LOGGER.warning("Ocorrência com ID " + id + " não encontrada para remoção");
        }
        return removido;
    }

    public List<Ocorrencia> listar() {
        return new ArrayList<>(lerOcorrencias()); // Retorna uma cópia para evitar modificações externas
    }

    public Ocorrencia buscarPorId(int id) {
        return lerOcorrencias().stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean setStatusOcorrencia(int id, String status) {
        if (status == null || status.trim().isEmpty()) {
            LOGGER.warning("Status inválido fornecido");
            return false;
        }
        
        Ocorrencia ocorrencia = buscarPorId(id);
        if (ocorrencia != null) {
            ocorrencia.setStatus(status);
            return atualizar(ocorrencia);
        } else {
            LOGGER.warning("Ocorrência com ID " + id + " não encontrada para atualização de status");
            return false;
        }
    }

    // Método para buscar ocorrências por ID do aluno
    public List<Ocorrencia> buscarPorAlunoId(int alunoId) {
        return lerOcorrencias().stream()
                .filter(o -> o.getAlunoID() == alunoId)
                .toList();
    }

    // Método para buscar ocorrências por ID da justificativa
    public List<Ocorrencia> buscarPorJustificativaId(int justificativaId) {
        return lerOcorrencias().stream()
                .filter(o -> o.getJustificativaID() == justificativaId)
                .toList();
    }

    // Método para verificar se existe uma ocorrência com determinado ID
    public boolean existeOcorrencia(int id) {
        return buscarPorId(id) != null;
    }

    // Método para listar ocorrências por status
    public List<Ocorrencia> buscarPorStatus(String status) {
        if (status == null) return new ArrayList<>();
        
        return lerOcorrencias().stream()
                .filter(o -> {
                    String statusOcorrencia = (o.getStatus() != null && !o.getStatus().isEmpty()) 
                        ? o.getStatus() : "Pendente";
                    return statusOcorrencia.equalsIgnoreCase(status);
                })
                .toList();
    }

    // Método para contar ocorrências por status
    public long contarPorStatus(String status) {
        return buscarPorStatus(status).size();
    }
}