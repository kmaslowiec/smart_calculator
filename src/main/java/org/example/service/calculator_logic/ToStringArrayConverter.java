package org.example.service.calculator_logic;

import org.example.utils.MyRegex;

public class ToStringArrayConverter {

    public String[] convert(String input) {
        return input.split(MyRegex.DIVIDE_STRING);
    }
}