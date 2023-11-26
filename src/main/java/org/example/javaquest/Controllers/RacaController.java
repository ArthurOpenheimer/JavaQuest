package org.example.javaquest.Controllers;

import java.util.Scanner;

import org.example.javaquest.Model.Raca;
import org.example.javaquest.Utils.ScannerSingleton;

public class RacaController extends GenericController<Raca> {

    @Override
    public Raca getTerminalInput() {
        System.out.println("===== Criar Raça do Personagem =====");
        Scanner scanner = ScannerSingleton.getInstance();

        System.out.print("Digite o nome da raça: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o traço racial da raça: ");
        String tracoRacial = scanner.nextLine();

        return new Raca(nome, tracoRacial);

    }

}
