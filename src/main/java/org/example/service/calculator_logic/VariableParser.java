package org.example.service.calculator_logic;

import org.example.exception.InvalidEntryException;
import org.example.utils.MyRegex;
import org.example.utils.MyStrings;

public class VariableParser {

    public String[] parseVariable(String[] input) throws InvalidEntryException {
        CalMemory calMemory = new CalMemory();
        String[] result = input.clone();

        for (int i = 0; i < input.length; i++) {
            if (input[i].matches(MyRegex.ONLY_LETTERS)) {
                try {
                    result[i] = calMemory.getBigNumber(input[i]) == null ?
                            Integer.toString(calMemory.getNumber(input[i])) :
                            String.valueOf(calMemory.getBigNumber(input[i]));
                } catch (NullPointerException e) {
                    throw new InvalidEntryException(MyStrings.UNASSIGNED_VARIABLE);
                }
            }
        }
        return result;
    }
}