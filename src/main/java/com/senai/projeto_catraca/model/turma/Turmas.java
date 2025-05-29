package com.senai.projeto_catraca.model.turma;

import java.util.ArrayList;
import java.util.List;

public class Turmas {
 private int id;
 private String nome;
 private String sigla;
 private String dataInicio;
 private int qntSemestre;
 private String horarioEntrada;
 private String periodo;
//A ideia é criar uma lista vazia de alunos na classe subturma para salvar la, mais o id que seria o numero da subturma.
//essa lista seria tipo, pra guardar os nomes de alunos
 private List<SubTurma> subTurmas = new ArrayList<>();

 public Turmas(int id, String nome, String sigla, String dataInicio, int qntSemestre, String horarioEntrada, String periodo, List<SubTurma> subTurmas) {
  this.id = id;
  this.nome = nome;
  this.sigla = sigla;
  this.dataInicio = dataInicio;
  this.qntSemestre = qntSemestre;
  this.horarioEntrada = horarioEntrada;
  this.periodo = periodo;
  this.subTurmas = subTurmas;
 }

 public Turmas(int id, String nome, String sigla, String dataInicio, int qntSemestres, String horarioEntrada, String periodo, String s) {
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
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
