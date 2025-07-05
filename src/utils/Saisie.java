package utils;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Saisie {
    private static Scanner scanner = new Scanner(System.in);

    public static int saisirInt(String message) {
        int val = 0;
        boolean ok = false;
        do {
            try {
                System.out.print(message);
                val = Integer.parseInt(scanner.nextLine());
                ok = true;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrez un nombre entier valide.");
            }
        } while (!ok);
        return val;
    }

    public static String saisirString(String message) {
        String val;
        do {
            System.out.print(message);
            val = scanner.nextLine().trim();
            if (val.isEmpty()) {
                System.out.println("❌ Le champ ne peut pas être vide.");
            }
        } while (val.isEmpty());
        return val;
    }

    public static String saisirStringNonVide(String message, int min, int max) {
        String val;
        do {
            System.out.print(message);
            val = scanner.nextLine().trim();
            if (val.isEmpty() || val.length() < min || val.length() > max) {
                System.out.println("❌ Saisie invalide. Longueur attendue : entre " + min + " et " + max + " caractères.");
            }
        } while (val.isEmpty() || val.length() < min || val.length() > max);
        return val;
    }

    public static LocalDate saisirDate(String message) {
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean ok = false;
        do {
            System.out.print(message + " (format AAAA-MM-JJ) : ");
            String val = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(val, formatter);
                ok = true;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Format de date invalide.");
            }
        } while (!ok);
        return date;
    }

    public static String saisirChoix(String message, String... choixValides) {
        String val;
        boolean ok;
        do {
            System.out.print(message + " " + "(choix : ");
            for (int i = 0; i < choixValides.length; i++) {
                System.out.print(choixValides[i]);
                if (i < choixValides.length - 1) System.out.print(", ");
            }
            System.out.print(") : ");
            val = scanner.nextLine().trim().toLowerCase();
            ok = false;
            for (String choix : choixValides) {
                if (val.equalsIgnoreCase(choix)) {
                    ok = true;
                    break;
                }
            }
            if (!ok) System.out.println("❌ Choix invalide.");
        } while (!ok);
        return val;
    }
}

