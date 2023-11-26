package org.example.javaquest.Controllers;

import java.util.Scanner;

import org.example.javaquest.Model.Classe;
import org.example.javaquest.Utils.ScannerSingleton;

public class ClasseController extends GenericController<Classe> {

    @Override
    public Classe getTerminalInput() {
        System.out.println("===== Criar Classe do Personagem =====");
        Scanner scanner = ScannerSingleton.getInstance();

        System.out.print("Digite o nome da classe: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a habilidade da classe: ");
        String habilidade = scanner.nextLine();

        return new Classe(nome, habilidade);
    }

}
