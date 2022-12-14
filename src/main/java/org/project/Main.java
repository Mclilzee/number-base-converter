package org.project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        userAction();
    }

    private static void userAction() {
        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String[] inputs = scanner.nextLine().toLowerCase().split(" ");

            if (inputs[0].equals("/exit")) {
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
            String input = scanner.nextLine().toLowerCase();

            if ("/back".equals(input)) {
                break;
            }

            if (input.matches("(\\d|[a-z])+(\\.(\\d|[a-z])+)?")) {
                printConversion(sourceBase, targetBase, input);
            } else {
                System.out.println("Incorrect Number format");
            }
        }
    }

    private static void printConversion(int sourceBase, int targetBase, String input) {
        String[] inputs = input.split("\\.");

        try {
            BigDecimal decimalResult = convertSourceToDecimal(sourceBase, inputs[0]);
            StringBuilder targetResult = new StringBuilder(convertDecimalToTarget(targetBase, decimalResult));

            if (targetResult.length() == 0) {
                targetResult.append("0");
            }

            if (inputs.length > 1) {
                BigDecimal fractionResult = convertSourceToDecimalFraction(sourceBase, inputs[1]);
                targetResult.append(".").append(convertDecimalFractionToTarget(targetBase, fractionResult));
            }

            System.out.printf("Conversion result: %s\n\n", targetResult);
        } catch (NumberFormatException e) {
            System.out.println("Wrong number provided for given source base!");
        }
    }

    private static BigDecimal convertSourceToDecimal(int source, String number) {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal exponent;

        for (int i = 0; i < number.length(); i++) {
            int digit = getDigit(number, number.length() - 1 - i);

            if (digit >= source) {
                throw new NumberFormatException();
            }
            exponent = BigDecimal.valueOf(source).pow(i);

            sum = sum.add(BigDecimal.valueOf(digit).multiply(exponent));
        }

        return sum;
    }

    private static BigDecimal convertSourceToDecimalFraction(int source, String number) {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal divider;

        for (int i = 0; i < number.length(); i++) {
            int digit = getDigit(number, i);

            if (digit >= source) {
                throw new NumberFormatException();
            }

            divider = BigDecimal.valueOf(source).pow(i + 1);
            sum = sum.add(BigDecimal.valueOf(digit).divide(divider, 10, RoundingMode.HALF_UP));
        }

        return sum;
    }

    private static int getDigit(String number, int i) {
        char digitChar = number.charAt(i);
        int digit;

        if (digitChar >= 'A') {
            digit = digitChar - 87;
        } else {
            digit = Integer.parseInt(String.valueOf(digitChar));
        }
        return digit;
    }

    private static String convertDecimalToTarget(int targetBase, BigDecimal number) {
        StringBuilder builder = new StringBuilder();
        while (number.compareTo(BigDecimal.ZERO) != 0) {
            int digit = number.remainder(BigDecimal.valueOf(targetBase)).intValue();
            builder.append(formatNumber(digit));
            number = number.divide(BigDecimal.valueOf(targetBase), RoundingMode.FLOOR);
        }

        return builder.reverse().toString();
    }

    private static String convertDecimalFractionToTarget(int targetBase, BigDecimal number) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 5) {
            number = number.multiply(BigDecimal.valueOf(targetBase));
            int digit = number.intValue();
            builder.append(formatNumber(digit));

            if (number.compareTo(BigDecimal.valueOf(digit)) >= 0) {
                number = number.subtract(BigDecimal.valueOf(digit));
            }
        }

        return builder.toString();
    }

    private static String formatNumber(int number) {

        if (number >= 10) {
            return String.valueOf((char) (number + 87));
        }

        return String.valueOf(number);
    }
}