package org.project;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

            if (input.matches("(\\d|[A-Z])+(\\.(\\d|[A-Z])+)?")) {
                printConversion(sourceBase, targetBase, input);
            } else {
                System.out.println("Incorrect Number format");
            }
        }
    }

    private static void printConversion(int sourceBase, int targetBase, String input) {
        String[] inputs = input.split("\\.");

        try {
            BigInteger decimalResult = convertSourceToDecimal(sourceBase, input);
            BigInteger result = convertDecimalToTarget(targetBase, decimalResult);
            System.out.printf("Conversion result: %s\n\n", result);
        } catch (NumberFormatException e) {
            System.out.println("Wrong number provided for given source base!");
        }
    }

    private static BigInteger convertSourceToDecimal(int source, String number) {
        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < number.length(); i++) {
            int digit = getDigit(number, i);

            if (digit >= source) {
                throw new NumberFormatException();
            }
            BigInteger exponent = BigInteger.valueOf(source).pow(i);
            BigInteger multipliedNumber = BigInteger.valueOf(digit).multiply(exponent);

            sum = sum.add(multipliedNumber);
        }

        return sum;
    }

    private static BigDecimal convertSourceToDecimalFraction(int source, String number) {
        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < number.length(); i++) {
            int digit = getDigit(number, i);

            if (digit >= source) {
                throw new NumberFormatException();
            }

            BigInteger divider = BigInteger.valueOf(source).pow(i + 1);
            sum = sum.add(BigInteger.valueOf(digit)).divide(divider);
        }

        return new BigDecimal("0." + sum);
    }

    private static int getDigit(String number, int i) {
        char digitChar = number.charAt(number.length() - 1 - i);
        int digit;

        if (digitChar >= 'A') {
            digit = digitChar - 55;
        } else {
            digit = Integer.parseInt(String.valueOf(digitChar));
        }
        return digit;
    }

    private static BigInteger convertDecimalToTarget(int targetBase, BigInteger number) {
        StringBuilder builder = new StringBuilder();
        while (!number.equals(BigInteger.ZERO)) {
            int digit = number.remainder(BigInteger.valueOf(targetBase)).intValue();
            builder.append(formatNumber(digit));
            number = number.divide(BigInteger.valueOf(targetBase));
        }

        return new BigInteger(builder.reverse().toString());
    }

    private static BigDecimal convertDecimalFractionToTarget(int targetBase, BigDecimal number) {
        StringBuilder builder = new StringBuilder("0.");
        while (!number.stripTrailingZeros().equals(BigDecimal.ZERO)) {
            number = number.multiply(BigDecimal.valueOf(targetBase));
            int digit = number.intValue();
            builder.append(formatNumber(digit));

            if (number.compareTo(BigDecimal.valueOf(digit)) >= 0) {
                number = number.subtract(BigDecimal.valueOf(digit));
            }
        }

        return new BigDecimal(builder.toString());
    }

    private static String formatNumber(int number) {

        if (number >= 10) {
            return String.valueOf((char) (number + 55));
        }

        return String.valueOf(number);
    }
}