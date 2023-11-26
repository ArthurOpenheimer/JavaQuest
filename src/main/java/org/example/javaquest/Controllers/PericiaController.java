package org.example.javaquest.Controllers;

import java.util.Scanner;

import org.example.javaquest.Model.Pericia;
import org.example.javaquest.Utils.ScannerSingleton;

public class PericiaController extends GenericController<Pericia> {

    @Override
    public Pericia getTerminalInput() {
        System.out.println("===== Criar Perícia do Personagem =====");
        Scanner scanner = ScannerSingleton.getInstance();

        System.out.print("Digite o nome da perícia: ");
        String nome = scanner.nextLine();

        int bonus = getTerminalNumberInput("Digite o bônus da perícia: ");

        return new Pericia(nome, bonus);

    }

}
