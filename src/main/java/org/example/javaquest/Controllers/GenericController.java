package org.example.javaquest.Controllers;

import java.util.ArrayList;
import java.util.Scanner;

import org.example.javaquest.Utils.ScannerSingleton;

public abstract class GenericController<T> {

    public abstract T getTerminalInput();

    public boolean askForMore(String prompt) {
        System.out.println(prompt);
        ArrayList<String> options = new ArrayList<String>();
        options.add("S");
        options.add("N");
        String answer = getTerminalOptionInput(options);

        return answer.toUpperCase().equals("S");
    }

    public ArrayList<T> getTerminalInputList() {
        ArrayList<T> list = new ArrayList<T>();
        boolean addMore = true;

        while (addMore) {
            list.add(getTerminalInput());
            addMore = askForMore("Deseja adicionar mais?");
        }

        return list;
    }

    public String getTerminalOptionInput(ArrayList<String> options) {
        Scanner scanner = ScannerSingleton.getInstance();
        String input = "";

        while (true) {
            System.out.println("Escolha uma opção: " + options.toString());
            input = scanner.nextLine();

            if (options.contains(input)) {
                break;
            } else {
                System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }

        return input;
    }

    public String getTerminalInputOrDefaultValue(String prompt, String defaultValue) {
        Scanner scanner = ScannerSingleton.getInstance();
        String input = "";

        System.out.print(prompt);
        input = scanner.nextLine();

        if (input.equals("")) {
            input = defaultValue;
        }

        return input;
    }

    public int getTerminalNumberInput(String prompt) {
        Scanner scanner = ScannerSingleton.getInstance();
        int input = 0;

        while (true) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Número inválido. Por favor, tente novamente.");
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        return input;
    }

    public int getTerminalNumberInputOrDefaultValue(String prompt, int defaultValue) {
        Scanner scanner = ScannerSingleton.getInstance();
        int input = 0;

        while (true) {
            System.out.print(prompt);
            String inputString = scanner.nextLine();

            if (inputString.equals("")) {
                input = defaultValue;
                break;
            } else {
                try {
                    input = Integer.parseInt(inputString);
                    break;
                } catch (Exception e) {
                    System.out.println("Número inválido. Por favor, tente novamente.");
                }
            }
        }

        scanner.nextLine();

        return input;
    }

}
