package org.example.service.calculator_logic;

import org.example.utils.MyRegex;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

public class Calculate {

    private Deque<String> stack = new ArrayDeque<>();

    public String calPostfix(String[] postfix) {
        boolean bigNumbers = String.join("", postfix).matches(MyRegex.TEN_DIGITS_NUMBER);
        for (String i : postfix) {
            if (i.matches(MyRegex.IS_NUMBER)) {
                stack.push(i);
            } else if (i.matches(MyRegex.OPERATORS)) {
                if (bigNumbers) {
                    BigInteger a = new BigInteger(stack.pop());
                    BigInteger b = new BigInteger(stack.pop());
                    stack.push(makeBigOperation(b, a, i));
                } else {
                    int a = Integer.parseInt(stack.pop());
                    int b = Integer.parseInt(stack.pop());
                    stack.push(makeOperation(a, b, i));
                }
            }
        }
        return stack.pop();
    }

    private String makeOperation(int a, int b, String operator) {
        int res = 0;
        switch (operator) {
            case "+":
                res = b + a;
                break;
            case "-":
                res = b - a;
                break;
            case "*":
                res = b * a;
                break;
            case "/":
                res = b / a;
                break;
        }

        return Integer.toString(res);
    }

    private String makeBigOperation(BigInteger a, BigInteger b, String operator) {
        BigInteger res = BigInteger.ZERO;
        switch (operator) {
            case "+":
                res = a.add(b);
                break;
            case "-":
                res = a.subtract(b);
                break;
            case "*":
                res = a.multiply(b);
                break;
            case "/":
                res = a.divide(b);
                break;
        }

        return res.toString();
    }
}