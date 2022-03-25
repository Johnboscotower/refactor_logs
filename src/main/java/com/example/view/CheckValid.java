package com.example.view;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class CheckValid {
    static final Logger logJava = Logger.getLogger(UserInterface.class);
    static Scanner scanner = new Scanner(System.in);

    public static int validInt() {
        int i;
        while(true) {
            try {
                i = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(NumberFormatException ex ) {
                logJava.warn("Not a valid number");
                System.out.println("Please enter a number");
            }
        }
        return i;
    }

    public static double validDouble() {
        double d;
        while(true) {
            try {
                d = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch(NumberFormatException ex ) {
                logJava.warn("Not a valid number");
                System.out.println("Please enter a number");
            }
        }
        return d;
    }

    public static String validString() {
        return scanner.nextLine();
    }

    public static boolean validBoolean() {
        while (true) {
            String answer = scanner.nextLine();
            if (answer.equals("y") || answer.equals("yes")) {
                return true;
            } else if (answer.equals("n") || answer.equals("no")) {
                return false;
            } else {
                logJava.warn("Invalid answer, didn't press y or n");
                System.out.println("Invalid answer. Please answer yes (y) or no (n)");
            }
        }
    }

    public static void closeScanner() {
        scanner.close();
        return;
    }
}
