package org.example.service.calculator_logic;

import org.example.exception.InvalidEntryException;
import org.example.utils.MyRegex;
import org.example.utils.MyStrings;

public class Validator {

    public boolean isAssignment(String assign) throws InvalidEntryException {
        if (assign.matches(MyRegex.IS_ASSIGNMENT)) {
            return true;
        } else if (assign.contains("=")) {
            String[] split = assign.split("=");
            if (split[0].matches(MyRegex.HAS_DIGITS)) {
                throw new InvalidEntryException(MyStrings.INVALID_IDENTIFIER);
            } else {
                throw new InvalidEntryException(MyStrings.INVALID_ASSIGNMENT);
            }
        } else {
            return false;
        }
    }

    public void validateAstrix(String input) throws InvalidEntryException {
        if (input.matches(MyRegex.HAS_MORE_THAN_0NE_ASTRIX)) {
            throw new InvalidEntryException(MyStrings.INVALID_EXPRESSION);
        }
    }

    public String commandsManager(String input) throws InvalidEntryException {
        switch (input) {
            case "/exit":
                return MyStrings.COMMAND_EXIT;
            case "/help":
                return MyStrings.COMMAND_HELP;
            default:
                if (input.matches(MyRegex.IS_NOT_REGISTERED_COMMAND)) {
                    throw new InvalidEntryException(MyStrings.UNKNOWN_COMMAND);
                }
        }
        return "";
    }
}