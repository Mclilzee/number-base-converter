package org.project;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        userAction();
    }

    private static void userAction() {
        while (true) {
            System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            String input = scanner.nextLine();

            switch (input) {
                case "/from":
                    System.out.println(convertDecimalToTargetBase());
                    break;
                case "/to":
                    System.out.println(convertBaseToDecimal());
                case "/exit":
                    return;
                default:
                    System.out.println("Incorrect option, choose between /from, /to and /exit");
            }
        }
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
            System.out.print("Enter target base: ");
            String input = scanner.nextLine();

            if ("2".equals(input) || "8".equals(input) || "16".equals(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Incorrect base type, choose from : 2, 8 and 16");
        }
    }

    private static String convertDecimalToTargetBase() {
        int decimalNumber = getDecimalNumberInput();
        int targetBase = getTargetBaseInput();

        StringBuilder builder = new StringBuilder();
        while (decimalNumber > 0) {
            builder.append(decimalNumber % targetBase);
            decimalNumber /= targetBase;
        }

        if (targetBase == 16) {
            return formatHexNumber(builder);
        }

        return builder.reverse().toString();
    }

    private static String convertBaseToDecimal() {
        String numberInput = getSourceNumberInput();
        int targetBase = getTargetBaseInput();

        if (targetBase == 16) {
            return convertHexToDecimal(numberInput);
        }

        return convertNonHexBaseToDecimal(numberInput, targetBase);
    }

    private static String convertNonHexBaseToDecimal(String input, int targetBase) {
        if (input.matches("[A-F]")) {
            throw new NumberFormatException();
        }

        int sum = 0;
        int size = input.length();

        for (int i = 0; i < size; i++) {
            String digitString = String.valueOf(input.charAt(size - 1 - i));
            int digit = Integer.parseInt(digitString);

            sum += digit * Math.pow(targetBase, i);
        }

        return String.valueOf(sum);
    }

    private static String getSourceNumberInput() {
        while (true) {
            System.out.print("Enter source number: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.matches("(\\d|[A-F])+")) {

                return input;
            }

            System.out.println("Incorrect number format");
        }
    }

    private static String formatHexNumber(StringBuilder builder) {
        String numberString = builder.toString();

        numberString = numberString.replaceAll("10", "A");
        numberString = numberString.replaceAll("11", "B");
        numberString = numberString.replaceAll("12", "C");
        numberString = numberString.replaceAll("13", "D");
        numberString = numberString.replaceAll("14", "E");
        numberString = numberString.replaceAll("15", "F");

        return new StringBuilder(numberString).reverse().toString();
    }
}