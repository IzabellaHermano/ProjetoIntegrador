package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.turma.horario.HorarioBase;
import com.senai.projeto_catraca.model.turma.horario.HorarioBaseDAO;

import java.time.LocalTime;
import java.util.List;

public class HorarioController {
     private final HorarioBaseDAO horarioBaseDAO = new HorarioBaseDAO();

     public String cadastrarHorario(int idProfessor, int idTurma){
      horarioBaseDAO.inserir(new HorarioBase(0, idProfessor, idTurma));
      return "Horário inserido";
     }
     public String atualizarHorario(int id, int idProfessor, int idTurma){
      horarioBaseDAO.atualizar(new HorarioBase(id, idProfessor, idTurma));
      return "Horário atualizado";
     }
     public String removerHorario(int id){
      horarioBaseDAO.remover(id);
      return "Horário removido";
     }

}
