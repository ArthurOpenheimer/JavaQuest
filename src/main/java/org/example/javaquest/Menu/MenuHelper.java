package org.example.javaquest.Menu;

import java.util.Scanner;

import org.example.javaquest.Controllers.PersonagemController;
import org.example.javaquest.Model.Personagem;
import org.example.javaquest.Utils.ScannerSingleton;

public class MenuHelper {
    public void startMenu() {
        Scanner scanner = ScannerSingleton.getInstance();

        boolean flag = true;

        while (flag) {
            System.out.println("===== Menu =====");
            System.out.println("1) Criar Personagem");
            System.out.println("2) Deletar Personagem");
            System.out.println("3) Ver Todos Personagens");
            System.out.println("4) Atualizar Personagem");
            System.out.println("5) Atacar Personagem");
            System.out.println("0) Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    criarPersonagem();
                    break;
                case 2:
                    deletarPersonagem();
                    break;
                case 3:
                    verTodosPersonagens();
                    break;
                case 4:
                    atualizarPersonagem();
                    break;
                case 5:
                    atacarPersonagem();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    flag = false;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();
        }

        ScannerSingleton.close();
    }

    private void criarPersonagem() {
        System.out.println("===== Criar Personagem =====");
        PersonagemController personagemController = new PersonagemController();
        Personagem personagem = personagemController.getTerminalInput();
        PersonagemController.create(personagem);
    }

    private void deletarPersonagem() {
        System.out.println("===== Deletar Personagem =====");
        PersonagemController personagemController = new PersonagemController();
        personagemController.deleteById();
    }

    private void verTodosPersonagens() {
        System.out.println("===== Personagens =====");
        PersonagemController.readAll();
    }

    private void atualizarPersonagem() {
        System.out.println("===== Atualizar Personagem =====");
        PersonagemController personagemController = new PersonagemController();
        personagemController.update();
    }

    private void atacarPersonagem() {
        System.out.println("===== Atacar Personagem =====");
        PersonagemController personagemController = new PersonagemController();
        personagemController.atacar();
    }
}
