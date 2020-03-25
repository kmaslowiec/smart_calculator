package org.example.service.calculator_logic;

public class OperatorsParser {

    ToStringArrayConverter toStringArrayConverter = new ToStringArrayConverter();

    public String plusParser(String input) {
        return input.replaceAll("[+]+", "+");
    }

    public String minusParser(String input) {
        String[] converted = toStringArrayConverter.convert(input);
        String[] result = new String[converted.length];

        for (int i = 0; i < converted.length; i++) {
            if (converted[i].matches("[-]*")) {
                result[i] = converted[i].length() % 2 == 0 ? "+" : "-";
            } else {
                result[i] = converted[i];
            }
        }

        return String.join("", result);
    }
}