package com.senai.projeto_catraca.model.curso;

import java.util.ArrayList;

public class UnidadeCurricularDAO {
    private static ArrayList<UnidadeCurricular> listaUCs = new ArrayList<>();

    public static void adicionarUC(UnidadeCurricular uc) {
        listaUCs.add(uc);
    }
    public static ArrayList<UnidadeCurricular> listarUCs() {
        return listaUCs;
    }
    public static UnidadeCurricular buscarUCporId(int id) {
        for (UnidadeCurricular uc : listaUCs) {
            if (uc.getId() == id) {
                return uc;
            }
        }
        return null;
    }
    public static ArrayList<UnidadeCurricular> listarUCsPorCurso(int idCurso) {
        ArrayList<UnidadeCurricular> resultado = new ArrayList<>();
        for (UnidadeCurricular uc : listaUCs) {
            if (uc.getIdCurso() == idCurso) {
                resultado.add(uc);
            }
        }
        return resultado;
    }
    public static boolean removerUC(int id) {
        UnidadeCurricular uc = buscarUCporId(id);
        if (uc != null) {
            listaUCs.remove(uc);
            return true;
        }
        return false;
    }
}

