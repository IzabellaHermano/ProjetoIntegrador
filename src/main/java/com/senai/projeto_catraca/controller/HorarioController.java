package com.senai.projeto_catraca.controller;

import java.util.List;
import java.util.Optional;

import com.senai.projeto_catraca.model.turma.horario.HorarioBase;
import com.senai.projeto_catraca.model.turma.horario.HorarioBaseDAO;

public class HorarioController {
    
    private final HorarioBaseDAO horarioBaseDAO = new HorarioBaseDAO();
    
    public String cadastrarHorario(int idProfessor, int idTurma){
        try {
            horarioBaseDAO.inserir(new HorarioBase(0, idProfessor, idTurma));
            return "Horário inserido com sucesso";
        } catch (Exception e) {
            return "Erro ao inserir horário: " + e.getMessage();
        }
    }
    
    public String atualizarHorario(int id, int idProfessor, int idTurma){
        try {
            horarioBaseDAO.atualizar(new HorarioBase(id, idProfessor, idTurma));
            return "Horário atualizado com sucesso";
        } catch (Exception e) {
            return "Erro ao atualizar horário: " + e.getMessage();
        }
    }
    
    public String removerHorario(int id){
        try {
            horarioBaseDAO.remover(id);
            return "Horário removido com sucesso";
        } catch (Exception e) {
            return "Erro ao remover horário: " + e.getMessage();
        }
    }
    
    public List<HorarioBase> listarHorario(){
        return horarioBaseDAO.listarTodos();
    }
    
    // Método adicional para buscar horário específico
    public Optional<HorarioBase> buscarHorarioPorId(int id){
        return horarioBaseDAO.listarTodos().stream()
                .filter(h -> h.getId() == id)
                .findFirst();
    }
}