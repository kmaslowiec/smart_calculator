package org.example.service.calculator_logic;

import org.example.exception.InvalidEntryException;
import org.example.utils.MyRegex;
import org.example.utils.MyStrings;

import java.util.Stack;

public class PostfixConverter {


    private static int getPrecedence(String operator) {
        switch (operator) {
            case "*":
            case "/":
                return 3;
            case "+":
            case "-":
                return 2;
        }
        return -1;
    }

    public String convertInfix(String[] infix) throws InvalidEntryException {

        StringBuilder result = new StringBuilder();

        Stack<String> stack = new Stack<>();

        for (String c : infix) {
            if (c.matches(MyRegex.IS_NUMBER)) {
                result.append(c).append(",");
            } else if ("(".equals(c)) {
                stack.push(c);
            } else if (")".equals(c)) {
                while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                    result.append(stack.pop()).append(",");
                }

                if (!stack.isEmpty() && !"(".equals(stack.peek())) {
                    throw new InvalidEntryException(MyStrings.INVALID_EXPRESSION);
                } else if (stack.isEmpty()) {
                    throw new InvalidEntryException(MyStrings.INVALID_EXPRESSION);
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    if ("(".equals(stack.peek())) {
                        throw new InvalidEntryException(MyStrings.INVALID_EXPRESSION);
                    }
                    result.append(stack.pop()).append(",");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if ("(".equals(stack.peek()))
                throw new InvalidEntryException(MyStrings.INVALID_EXPRESSION);
            result.append(stack.pop()).append(",");
        }
        return result.toString();

    }
}