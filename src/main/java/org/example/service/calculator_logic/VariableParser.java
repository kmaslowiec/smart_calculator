package org.example.service.calculator_logic;

import org.example.exception.InvalidEntryException;
import org.example.utils.MyRegex;
import org.example.utils.MyStrings;

public class VariableParser {

    public String[] parseVariable(String[] input) throws InvalidEntryException {
        Memory memory = new Memory();
        String[] result = input.clone();

        for (int i = 0; i < input.length; i++) {
            if (input[i].matches(MyRegex.ONLY_LETTERS)) {
                try {
                    result[i] = memory.getBigNumber(input[i]) == null ?
                            Integer.toString(memory.getNumber(input[i])) :
                            String.valueOf(memory.getBigNumber(input[i]));
                } catch (NullPointerException e) {
                    throw new InvalidEntryException(MyStrings.UNASSIGNED_VARIABLE);
                }
            }
        }
        return result;
    }
}