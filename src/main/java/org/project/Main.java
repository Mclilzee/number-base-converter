package org.project;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        userAction();
    }

    private static void userAction() {
        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String[] inputs = scanner.nextLine().split(" ");

            if (inputs.length > 2 || inputs.length < 1) {
                System.out.println("Wrong input provided");
            }

            if (inputs[0].equals("/exit")) {
                break;
            }

            handleBaseInput(inputs[0], inputs[1]);
        }
    }

    private static void handleBaseInput(String sourceBaseInput, String targetBaseInput) {
        if (sourceBaseInput.matches("\\d{1,2}") && targetBaseInput.matches("\\d{1,2}")) {
            int sourceBase = Integer.parseInt(sourceBaseInput);
            int targetBase = Integer.parseInt(targetBaseInput);

            if (sourceBase >= 2 && sourceBase <= 32 && targetBase >= 2 && targetBase <= 32) {
                convertFromSourceToTarget(sourceBase, targetBase);
            }
        }
    }

    private static void convertFromSourceToTarget(int sourceBase, int targetBase) {
        while (true) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", sourceBase, targetBase);
            String input = scanner.nextLine();

            if ("/back".equals(input)) {
                break;
            }

            if (input.matches("(\\d|[A-Z])+")) {
                printConversion(sourceBase, targetBase);
            }

            System.out.println("Incorrect Number format");
        }
    }

    private static void printConversion(int sourceBase, int targetBase) {
        StringBuilder builder = new StringBuilder();
        while (decimalNumber.compareTo(BigInteger.ZERO) > 0) {
            builder.append(decimalNumber.mod(BigInteger.valueOf(sourceBase)));
            decimalNumber = decimalNumber.divide(BigInteger.valueOf(sourceBase));
        }

        System.out.printf("Conversion result: %s", formatNumber(builder.reverse().toString(), sourceBase));
    }

    private static String convertHexToDecimal(String input) {
        final int BASE = 16;

        int size = input.length();
        int sum = 0;

        for (int i = 0; i < size; i++) {
            String digitString = String.valueOf(input.charAt(size - 1 - i));
            int digit = changeHexToNumber(digitString);

            if (digit >= BASE) {
                throw new NumberFormatException();
            }

            sum += digit * Math.pow(BASE, i);
        }

        return String.valueOf(sum);
    }

    private static String convertNonHexBaseToDecimal(String input, int targetBase) {
        int sum = 0;
        int size = input.length();

        for (int i = 0; i < size; i++) {
            String digitString = String.valueOf(input.charAt(size - 1 - i));
            int digit = Integer.parseInt(digitString);

            if (digit >= targetBase) {
                throw new NumberFormatException();
            }

            sum += digit * Math.pow(targetBase, i);
        }

        return String.valueOf(sum);
    }

    private static String formatNumber(String number, int base) {
        if (base <= 9) {
            return number;
        }

        for (int i = 10; i < base; i++) {
            String letter = String.valueOf((char) (55 + i));
            number = number.replaceAll(String.valueOf(i), letter);
        }

        return number;
    }
}