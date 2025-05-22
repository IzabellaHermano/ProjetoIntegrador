package com.senai.projeto_catraca.model.turma;

import java.util.ArrayList;
import java.util.List;

public class Turmas {
 private String nome;
 private String sigla;
 private int dataInicio;
 private int qntSemestre;
 private int horarioEntrada;
 private int periodo;
//  A explicação para a list subturma seria especificar um ID que seria o numero da subturma que iria pra
// classe da sub turma e de la criar a tabela de alunos
 private List<SubTurma> subTurmas = new ArrayList<>();

 public Turmas(String nome, String sigla, int dataInicio, int qntSemestre, int horarioEntrada, int periodo, List<SubTurma> subTurmas) {
  this.nome = nome;
  this.sigla = sigla;
  this.dataInicio = dataInicio;
  this.qntSemestre = qntSemestre;
  this.horarioEntrada = horarioEntrada;
  this.periodo = periodo;
  this.subTurmas = subTurmas;
 }

 public List<SubTurma> getSubTurmas() {
  return subTurmas;
 }

 public void setSubTurmas(List<SubTurma> subTurmas) {
  this.subTurmas = subTurmas;
 }

 public String getNome() {
  return nome;
 }

 public void setNome(String nome) {
  this.nome = nome;
 }

 public String getSigla() {
  return sigla;
 }

 public void setSigla(String sigla) {
  this.sigla = sigla;
 }

 public int getDataInicio() {
  return dataInicio;
 }

 public void setDataInicio(int dataInicio) {
  this.dataInicio = dataInicio;
 }

 public int getQntSemestre() {
  return qntSemestre;
 }

 public void setQntSemestre(int qntSemestre) {
  this.qntSemestre = qntSemestre;
 }

 public int getHorarioEntrada() {
  return horarioEntrada;
 }

 public void setHorarioEntrada(int horarioEntrada) {
  this.horarioEntrada = horarioEntrada;
 }

 public int getPeriodo() {
  return periodo;
 }

 public void setPeriodo(int periodo) {
  this.periodo = periodo;
 }
}
