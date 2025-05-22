package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.controller.CoordenadorController;
import com.senai.projeto_catraca.model.usuario.Coordenador;

import java.util.Scanner;

public class CoordenadorView {
    private final CoordenadorController controller = new CoordenadorController();
    private final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        CoordenadorView coordenadorView = new CoordenadorView();
        coordenadorView.menu();
    }
    public void menu(){
        System.out.println("--Coordenador--");
        String menu = """
                ______________________________
                |1. Cadastrar Coordenador    |
                |2. Deletar Coordenador      |
                |3. Atualizar Coordenador    |
                |4. Exibir Coordenador       |
                |5. Sair                     |
                |____________________________|
               """;

        int opcao;
        switch (opcao)
    }

    public void cadastrar(){
        System.out.println("Digite as seguintes informações: ");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();
        System.out.println("CPF: ");
        String CPF = scanner.nextLine();
        System.out.println("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();
        controller.cadastrarCoordenador(nome, senha, CPF, endereco, telefone);
    }
}
