package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.turma.SubTurma;
import com.senai.projeto_catraca.model.turma.Turmas;
import com.senai.projeto_catraca.model.dao.json.TurmasDAO;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaController {

    private final TurmasDAO turmasDAO = new TurmasDAO();

    public String cadastrarTurma(String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
        turmasDAO.inserir(new Turmas(0, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, subTurmas));
        return "Turma cadastrada com sucesso.";
    }

    public String atualizarTurma(int id, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
        turmasDAO.atualizar(new Turmas(id, sigla, dataInicio, qntSemestres, horarioEntrada, periodo, subTurmas));
        return "Turma atualizada com sucesso.";
    }

    public String removerTurma(int id) {
        turmasDAO.remover(id);
        return "Turma removida com sucesso.";
    }

    public List<Turmas> listarTurmas() {
        return turmasDAO.listarTodos();
    }

    public String removerSubTurma(int idTurma, int idSB) {
        turmasDAO.removerSB(idTurma, idSB);
        return "SubTurma removida.";
    }

//    public String removerAlunos(int idTurma, int idAluno) {
//        turmasDAO.removerAluno(idTurma, idAluno);
//        return "SubTurma removida.";
//    }

    public SubTurma cadastrarSub(int idsubTurma, List<Aluno> listaAlunos, String nomealuno, String senha, String cpf, int idAluno, String endereco, String telefone, String idCartaoRfid, int matricula) {
        Aluno aluno = new Aluno(nomealuno, senha, cpf, idAluno, endereco, telefone, idCartaoRfid,
                new ArrayList<>(), new ArrayList<>(), matricula);
        listaAlunos.add(aluno);
        SubTurma subTurma = new SubTurma(idsubTurma, listaAlunos);
        turmasDAO.cadastrarSubTurma(idsubTurma, subTurma, listaAlunos);
        return subTurma;
    }


    public SubTurma atualizarSub (int idsubTurma, int idTurma, List<Aluno> alunoList){
        SubTurma subTurma = new SubTurma(idsubTurma, alunoList);
        turmasDAO.atualizarSubTurma(idTurma, subTurma);
        return subTurma;
    }


    public Optional<Turmas> buscarPorId(int idTurma) {
        return turmasDAO.buscarPorId(idTurma);
    }

}

//    public String cadastrarAlunos(int id, String nomealuno, String senha, String cpf, String endereco, String telefone, String idCartaoRfid, int matricula) {
//
//        Aluno aluno = new Aluno(
//                nomealuno,
//                senha,
//                cpf,
//                0, // ID do usuário, pode ser gerado ou definido de outra forma
//                endereco,
//                telefone,
//                idCartaoRfid,
//                new ArrayList<>(), // justificativas
//                new ArrayList<>(), // ocorrências
//                matricula
//        );
//
//        // Criando a lista e adicionando o aluno
//        List<Aluno> alunos = new ArrayList<>();
//        alunos.add(aluno);
//
//        SubTurma subTurma = new SubTurma(id, alunos);
//        // Adicionando à subturma de ID 0 (ou substitua pelo ID real)
//       // turmasDAO.adicionarAlunosNaSubturma(subTurma, alunos);
//        return "Alunos adicionado a Turma";
//    }