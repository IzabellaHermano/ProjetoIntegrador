package com.senai.projeto_catraca.model.turma;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmasDAO {
    private final String caminho = "turma.json";
    private final String caminhoA = "alunos.json";
    private final Gson gson = new Gson();
    private final List<Turmas> turma;

    public TurmasDAO() {
        this.turma = carregar();
    }

    private List<Turmas> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Turmas>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Turmas> carregar2() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Turmas>>() {
            }.getType();
            List<Turmas> lista = gson.fromJson(reader, listType);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    private void salvar(List<Turmas> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarAlunos(List<Aluno> lista) {
        try (FileWriter writer = new FileWriter(caminhoA)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Turmas turmas) {
        int novoId = turma.stream().mapToInt(Turmas::getId).max().orElse(0) + 1;
        turmas.setId(novoId);
        turma.add(turmas);
        salvar(turma);
    }

    public void atualizar(Turmas turmas) {
        for (int i = 0; i < turma.size(); i++) {
            if (turma.get(i).getId() == turmas.getId()) {
                turma.set(i, turmas);
                break;
            }
        }
        salvar(turma);
    }

    public void remover(int id) {
        turma.removeIf(p -> p.getId() == id);
        salvar(turma);
    }


    public Optional<Turmas> buscarPorId(int id) {
        return turma.stream().filter(t -> t.getId() == id).findFirst();
    }


    public void cadastrarSubTurma(int idTurma, SubTurma subTurma, List<Aluno> alunos) {
        Turmas t = turma.stream().filter(c -> c.getId() == idTurma).findFirst().orElse(null);
        if (t != null) {
            int novoIdSub = t.getSubTurmas().stream().mapToInt(SubTurma::getId).max().orElse(0) + 1;
            subTurma.setId(novoIdSub);
            t.getSubTurmas().add(subTurma);
            salvar(turma);
            salvarAlunos(alunos);
        }
    }

    public void atualizarSubTurma(int idTurma, SubTurma subTurma, List<Aluno> alunos) {
        Turmas t = turma.stream().filter(c -> c.getId() == idTurma).findFirst().orElse(null);
        if (t != null) {
            List<SubTurma> subTurmas = t.getSubTurmas();
            for (SubTurma s : subTurmas) {
                if (s.getId() == subTurma.getId()) {
                    s.getAlunos().addAll(subTurma.getAlunos());
                    salvar(turma);
                    salvarAlunos(alunos);
                    break;
                }
            }
        }
    }

    public void removerSB(int idTurma, int idSB) {
        Optional<Turmas> turmasOpt = buscarPorId(idTurma);
        if (turmasOpt.isPresent()) {
            Turmas t = turmasOpt.get();
            t.getSubTurmas().removeIf(sb -> sb.getId() == idSB);
            salvar(turma);
        }
    }

//    public void removerAluno(int idTurma, int idAluno) {
//        Optional<Turmas> turmasOpt = buscarPorId(idTurma);
//        if (turmasOpt.isPresent()) {
//            Turmas t = turmasOpt.get();
//            t.getAlunos().removeIf(sb -> sb.getId() == idAluno);
//            salvar(turma);
//        }
//    }

    public void adicionarAlunosNaSubturma(SubTurma subTurma, List<Aluno> alunos) {
        turma.stream()
                .filter(turma ->
                        turma.getSubTurmas().contains(subTurma)
                ).findFirst().flatMap(
                        turma -> turma.getSubTurmas().stream()
                                .filter(
                                        s -> s.equals(subTurma)
                                ).findFirst()
                ).ifPresent(
                        s -> s.getAlunos().addAll(alunos)
                );
    }


    public List<Turmas> listarTodos() {
        return turma;
    }

//    public Optional<Turmas> buscarPorAluno(Aluno aluno) {
//        return turma.stream()
//                .filter(t ->
//                        t.getSubTurmas().stream()
//                                .anyMatch(subTurma ->
//                                        subTurma.getAlunos().stream()
//                                                .anyMatch(a -> a.equals(aluno))
//                                )
//                ).findFirst();
//    }
//
}