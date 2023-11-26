package org.example.javaquest.Controllers;

import java.util.ArrayList;
import java.util.Scanner;

import org.example.javaquest.Model.Arma;
import org.example.javaquest.Model.Ferramenta;
import org.example.javaquest.Model.Item;
import org.example.javaquest.Utils.ScannerSingleton;

public class ItemController extends GenericController<Item> {

    @Override
    public Item getTerminalInput() {
        System.out.println("===== Criar Item do Personagem =====");
        Scanner scanner = ScannerSingleton.getInstance();

        System.out.print("Digite o nome do item: ");
        String nome = scanner.nextLine();

        System.out.println("Qual o tipo do Item?");
        ArrayList<String> options = new ArrayList<String>();
        options.add("Arma");
        options.add("Ferramenta");
        String tipoString = getTerminalOptionInput(options);

        int tipo = tipoString.equals("Arma") ? Arma.tipo : Ferramenta.tipo;

        if (tipo == 1) {
            int dano = getTerminalNumberInput("Digite o dano da arma: ");
            return new Arma(nome, dano);
        } else {
            System.out.print("Digite a descrição da ferramenta: ");
            String descricao = scanner.nextLine();
            return new Ferramenta(nome, descricao);
        }

    }

}
