package org.example.service.impl;

import org.example.exception.InvalidEntryException;
import org.example.service.CalculatorService;
import org.example.service.calculator_logic.CalMemory;
import org.example.service.calculator_logic.Calculate;
import org.example.service.calculator_logic.OperatorsParser;
import org.example.service.calculator_logic.PostfixConverter;
import org.example.service.calculator_logic.ToStringArrayConverter;
import org.example.service.calculator_logic.Validator;
import org.example.service.calculator_logic.VariableParser;
import org.example.utils.MyConditions;

import java.math.BigInteger;


public class CalculatorServiceImpl implements CalculatorService {

    private Validator valid;
    private ToStringArrayConverter inputConverter;
    private PostfixConverter postfixConverter;
    private OperatorsParser operatorsParser;
    private VariableParser variableParser;
    private Calculate calculate;
    private CalMemory calMemory;

    public CalculatorServiceImpl() {
        initComponents();
    }

    public void initComponents() {
        this.valid = new Validator();
        this.inputConverter = new ToStringArrayConverter();
        this.postfixConverter = new PostfixConverter();
        this.operatorsParser = new OperatorsParser();
        this.variableParser = new VariableParser();
        this.calculate = new Calculate();
        this.calMemory = new CalMemory();
    }

    @Override
    public String calculate(String input) {
        try {
            if (input.contains("/")) {
                return valid.commandsManager(input);
            } else {
                if (input.contains("*")) {
                    valid.validateAstrix(input);
                }
                if (input.contains("+")) {
                    input = operatorsParser.plusParser(input);
                }
                if (input.contains("-")) {
                    input = operatorsParser.minusParser(input);
                }

                String[] converted = inputConverter.convert(input);
                if (valid.isAssignment(input)) {
                    if (MyConditions.isBigNumber(converted[2])) {
                        calMemory.addToBigMemory(converted[0], new BigInteger(converted[2]));
                    } else {
                        calMemory.addToMemory(converted[0], Integer.parseInt(converted[2]));
                    }
                } else {
                    String[] numAndOperators = variableParser.parseVariable(converted);
                    if (converted.length > 1) {
                        calMemory.setResult(true);
                    } // check if the calculation is result
                    String postfix = postfixConverter.convertInfix(numAndOperators);
                    String[] splitPostfix = postfix.split(",");
                    return calculate.calPostfix(splitPostfix);
                }
            }
        } catch (InvalidEntryException e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public void clearIsResult() {
        calMemory.setResult(false);
    }
}