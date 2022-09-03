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
            String[] inputs = scanner.nextLine().toUpperCase().split(" ");

            if (inputs[0].equals("/EXIT")) {
                break;
            }

            if (inputs.length != 2) {
                System.out.println("Wrong input provided");
                continue;
            }



            handleBaseInput(inputs[0], inputs[1]);
        }
    }

    private static void handleBaseInput(String sourceBaseInput, String targetBaseInput) {
        if (sourceBaseInput.matches("\\d{1,2}") && targetBaseInput.matches("\\d{1,2}")) {
            int sourceBase = Integer.parseInt(sourceBaseInput);
            int targetBase = Integer.parseInt(targetBaseInput);

            if (sourceBase >= 2 && sourceBase <= 36 && targetBase >= 2 && targetBase <= 36) {
                convertFromSourceToTarget(sourceBase, targetBase);
                return;
            }
        }

        System.out.println("Bases should be between 2 and 36 inclusive");
    }

    private static void convertFromSourceToTarget(int sourceBase, int targetBase) {
        while (true) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", sourceBase, targetBase);
            String input = scanner.nextLine().toUpperCase();

            if ("/BACK".equals(input)) {
                break;
            }

            if (input.matches("(\\d|[A-Z])+")) {
                printConversion(sourceBase, targetBase, input);
            } else {
                System.out.println("Incorrect Number format");
            }
        }
    }

    private static void printConversion(int sourceBase, int targetBase, String input) {
        BigInteger number = getBigIntegerFormat(input);

        StringBuilder builder = new StringBuilder();
        while (number.compareTo(BigInteger.ZERO) > 0) {
            builder.append(number.mod(BigInteger.valueOf(targetBase)));
            number = number.divide(BigInteger.valueOf(targetBase));
        }

        System.out.printf("Conversion result: %s\n\n", formatNumber(builder.reverse().toString(), sourceBase));
    }

    private static BigInteger getBigIntegerFormat(String input) {
        for (char i = 'A'; i <= 'Z'; i++) {
            int numberDigit = i - 55;
            input = input.replaceAll(String.valueOf(i), String.valueOf(numberDigit));
        }

        return new BigInteger(input);
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