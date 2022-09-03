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

            String result = handleBaseInput(inputs[0], inputs[1]);

            if (!result.isEmpty()) {
                System.out.println("Conversion result: " + result);
                System.out.println();
            }
        }
    }

    private static String handleBaseInput(String sourceBaseInput, String targetBaseInput) {
        if (sourceBaseInput.matches("\\d{1,2}") && targetBaseInput.matches("\\d{1,2}")) {
            int sourceBase = Integer.parseInt(sourceBaseInput);
            int targetBase = Integer.parseInt(targetBaseInput);

            if (sourceBase >= 2 && sourceBase <= 32 && targetBase >= 2 && targetBase <= 32) {
                return convertFromSourceToTarget(sourceBase, targetBase);
            }
        }

        return "";
    }

    private static String convertFromSourceToTarget(int sourceBase, int targetBase) {

        while (true) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", sourceBase, targetBase);
            String input = scanner.next();
        }

        System.out.print("Enter target base: ");
        int sourceBase = handleBaseInput();

        StringBuilder builder = new StringBuilder();
        while (decimalNumber.compareTo(BigInteger.ZERO) > 0) {
            builder.append(decimalNumber.mod(BigInteger.valueOf(sourceBase)));
            decimalNumber = decimalNumber.divide(BigInteger.valueOf(sourceBase));
        }

        return formatNumber(builder.reverse().toString(), sourceBase);
    }

    private static void printBaseToDecimalConversion() {
        String numberInput = getSourceNumberInput();

        System.out.print("Enter source base: ");
        int targetBase = handleBaseInput();

        System.out.print("Conversion to decimal result: ");
        try {
            if (targetBase == 16) {
                System.out.println(convertHexToDecimal(numberInput));
            } else {
                System.out.println(convertNonHexBaseToDecimal(numberInput, targetBase));
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format provided for the base!");
        }
        System.out.println();
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

    private static int changeHexToNumber(String input) {
        return switch (input) {
            case "A" -> 10;
            case "B" -> 11;
            case "C" -> 12;
            case "D" -> 13;
            case "E" -> 14;
            case "F" -> 15;
            default -> Integer.parseInt(input);
        };
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