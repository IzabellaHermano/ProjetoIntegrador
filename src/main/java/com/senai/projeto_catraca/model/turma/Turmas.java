package com.senai.projeto_catraca.model.turma;

import java.util.List;

public class Turmas {
 private int id;
 private String sigla;
 private String dataInicio;
 private int qntSemestre;
 private String horarioEntrada;
 private String periodo;
//A ideia é criar uma lista vazia de alunos na classe subturma para salvar la, mais o id que seria o numero da subturma.
//essa lista seria tipo, pra guardar os nomes de alunos.
 //Subturma voltou a ser id, por causa que é muito complicado bota list no MySQL, a pedido do Professor.
 private List<SubTurma> subTurmas;

 public Turmas(int id, String sigla, String dataInicio, int qntSemestre, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
  this.id = id;
  this.sigla = sigla;
  this.dataInicio = dataInicio;
  this.qntSemestre = qntSemestre;
  this.horarioEntrada = horarioEntrada;
  this.periodo = periodo;
  this.subTurmas = subTurmas;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getSigla() {
  return sigla;
 }

 public void setSigla(String sigla) {
  this.sigla = sigla;
 }

 public String getDataInicio() {
  return dataInicio;
 }

 public void setDataInicio(String dataInicio) {
  this.dataInicio = dataInicio;
 }

 public int getQntSemestre() {
  return qntSemestre;
 }

 public void setQntSemestre(int qntSemestre) {
  this.qntSemestre = qntSemestre;
 }

 public String getHorarioEntrada() {
  return horarioEntrada;
 }

 public void setHorarioEntrada(String horarioEntrada) {
  this.horarioEntrada = horarioEntrada;
 }

 public String getPeriodo() {
  return periodo;
 }

 public void setPeriodo(String periodo) {
  this.periodo = periodo;
 }

 public List<SubTurma> getSubTurmas() {
  return subTurmas;
 }

 public void setSubTurmas(List<SubTurma> subTurmas) {
  this.subTurmas = subTurmas;
 }
}
