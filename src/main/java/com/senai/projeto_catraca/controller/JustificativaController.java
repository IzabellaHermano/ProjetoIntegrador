package com.senai.projeto_catraca.controller;

import com.senai.projeto_catraca.model.usuario.aluno.Justificativa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JustificativaController {

    private final List<Justificativa> listaJustificativas = new ArrayList<>();
    private int proximoId = 1;

    public void cadastrarJustificativa(String tipo, String descricao, LocalDate data, String anexo) {
        Justificativa nova = new Justificativa(proximoId++, tipo, descricao, data, anexo);
        listaJustificativas.add(nova);
        System.out.println("Justificativa cadastrada com sucesso.");
    }

    public List<Justificativa> listarJustificativa() {
        return listaJustificativas;
    }

    public void removerJustificativa(int id) {
        Optional<Justificativa> justificativaEncontrada = listaJustificativas.stream()
                .filter(j -> j.getId() == id)
                .findFirst();

        if (justificativaEncontrada.isPresent()) {
            listaJustificativas.remove(justificativaEncontrada.get());
            System.out.println("Justificativa removida com sucesso.");
        } else {
            System.out.println("Justificativa com ID " + id + " não encontrada.");
        }
    }

    public void atualizarJustificativa(String tipo, String descricao, LocalDate data, String anexo, int id) {
        Optional<Justificativa> justificativaEncontrada = listaJustificativas.stream()
                .filter(j -> j.getId() == id)
                .findFirst();

        if (justificativaEncontrada.isPresent()) {
            Justificativa j = justificativaEncontrada.get();
            j.setTipo(tipo);
            j.setDescricao(descricao);
            j.setData(data);
            j.setAnexo(anexo);
            System.out.println("Justificativa atualizada com sucesso.");
        } else {
            System.out.println("Justificativa com ID " + id + " não encontrada.");
        }
    }
}
