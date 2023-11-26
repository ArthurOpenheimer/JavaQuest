package org.example.javaquest.Utils;

import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner scanner;

    public static Scanner getInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
}
