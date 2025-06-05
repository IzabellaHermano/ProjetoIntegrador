package com.senai.projeto_catraca.model.dao.mysql;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.turma.horario.HorarioBase;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HorarioBaseDAO {

    public void inserir(HorarioBase horario) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO horario (id_aluno, id_professor) VALUES (?, ?)");
            stmt.setInt(1, horario.getIdTurma());
            stmt.setInt(2, horario.getIdProfessor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(HorarioBase horario) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE horario SET id_aluno = ?, id_professor = ? WHERE id = ?");
            stmt.setInt(1, horario.getIdTurma());
            stmt.setInt(2, horario.getIdProfessor());
            stmt.setInt(3, horario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM horario WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<HorarioBase> buscarHorarioDoAluno(int idAluno) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM horario WHERE id_aluno = ?");
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new HorarioBase(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_professor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<HorarioBase> listarTodos() {
        List<HorarioBase> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM horario");
            while (rs.next()) {
                lista.add(new HorarioBase(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_professor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
