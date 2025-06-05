package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.model.usuario.AQV;
import com.senai.projeto_catraca.model.usuario.Coordenador;
import com.senai.projeto_catraca.model.usuario.Professor;
import com.senai.projeto_catraca.model.usuario.Usuario;
import com.senai.projeto_catraca.model.usuario.aluno.Aluno;

import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipalView {
    private static  final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        logar();
    }
    public static void logar(){
        Optional<Usuario> usuarioLogin = new LoginView().exibirLogin();
        usuarioLogin.ifPresent(MenuPrincipalView::redirecionarMenu);
    }

    private  static  void  redirecionarMenu (Usuario usuario){
        switch (usuario.getTipo()){
            case "Aluno" -> menuAluno((Aluno)usuario);
            case "Professor" -> menuProfessor((Professor) usuario);
            case "Coordenador" -> menuCoordenador((Coordenador) usuario);
            case "Aqv" -> menuAqv((AQV) usuario);
            default -> {
                System.out.println("Usuário Inválido!");
                logar();
            }
        }
    }
    private  static  void  menuAluno(Aluno aluno){

    }
    private  static  void  menuProfessor(Professor professor){

    }
    private  static  void  menuCoordenador(Coordenador coordenador){

    }
    private  static  void  menuAqv(AQV aqv){

    }

}
