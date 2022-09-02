package org.project;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    }

    private static void userAction() {
        int decimalNumber = getDecimalNumberInput();
        int targetBase = getTargetBaseInput();
    }

    private static int getDecimalNumberInput() {
        while (true) {
            System.out.print("Enter number in decimal system: ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }

            System.out.println("Incorrect number format");
        }
    }

    private static int getTargetBaseInput() {
        while (true) {
            System.out.println("Enter target base: ");
            String input = scanner.nextLine();

            if ("2".equals(input) || "8".equals(input) || "16".equals(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Incorrect base type, choose from : 2, 8 and 16");
        }
    }

    private static String convertDecimalToTargetBase(int decimal, int targetBase) {
        StringBuilder builder = new StringBuilder();
        while (decimal > 0) {
            builder.append(decimal % targetBase);
            decimal /= targetBase;
        }

        if (targetBase == 16) {
            return formatHexNumber(builder);
        }

        return builder.toString();
    }

    private static String formatHexNumber(StringBuilder builder) {
        String number = builder.toString();

        for (int i = 0; i < 6; i++) {
            number = number.replace("" + 10 + i, 'A' + 1 + "");
        }

        return number;
    }
}