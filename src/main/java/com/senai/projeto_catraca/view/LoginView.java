package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.LoginController;
import com.senai.projeto_catraca.model.usuario.Usuario;

import java.util.Optional;
import java.util.Scanner;

public class LoginView {
    private final Scanner scanner = new Scanner(System.in);
    private final LoginController loginController = new LoginController();

    public Optional <Usuario> exibirLogin(){
        System.out.println("SENAI ANCHIETA\nLOGIN:");
        System.out.print("|Nome: ");
        String nome = scanner.nextLine();

        System.out.print("|Senha: ");
        String senha = scanner.nextLine();

        Optional <Usuario> usuario = loginController.autenticarUsuario(nome,senha);
        if (usuario.isEmpty()) System.out.println("\nUsuário não encontrado.Tente novamente");
        return usuario;
    }
}
