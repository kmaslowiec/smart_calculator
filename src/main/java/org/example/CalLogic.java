package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalLogic {


    String[] variablesArray;
    HashMap<String, Integer> map = new HashMap<>();
    App app = new App();
    String info = "";

    public String logic(String input) {
        CalLogic m = new CalLogic();
        String result = "";
        boolean flag = true;
        try {
            int i = Integer.parseInt(input);
            result = Integer.toString(i);
        } catch (NumberFormatException e) {
            switch (input) {
                case "/exit":
                    result = "Bye!";
                    flag = false;
                    System.exit(0);
                    break;
                case "/help":
                    result = "Konrad is awesome!";
                    break;
                default:
                    if (input.split(" ").length == 1 && input.matches("[^=]*")) {
                        try {
                            if (input.contains("/")) {
                                if (info.isEmpty()) result = "Unknown command";
                            } else if (input.matches("[a-zA-Z]+")) {
                                String smallRes = app.getMap().get(input).toString();
                                if (info.isEmpty()) result = smallRes;
                            }
                        } catch (NullPointerException ex) {
                            if (info.isEmpty()) result = "Unknown variable";
                        }
                    } else {
                        String output = m.newValidation(input);
                        result = m.throwExceptionOrCalculate(output);


                    }
            }
        }
        return result;
    }

    public boolean isMinus(String minus) {
        return minus.matches("[-]*") && checkMinus(minus);
    }

    public boolean checkMinus(String minus) {
        return minus.length() % 2 != 0;
    }

    public boolean isDigit(String s) {
        return s.matches("^[+-]?[0-9]+$");
    }

    public int doMath(String[] arr) {
        int res = 0;
        boolean sub = false;
        for (String s : arr) {
            if (isDigit(s) && !sub) {
                res += Integer.parseInt(s);
            } else if (isMinus(s)) {
                sub = true;
            } else if (isDigit(s) && sub) {
                res -= Integer.parseInt(s);
                sub = false;
            }
        }
        return res;
    }

    public String throwExceptionOrCalculate(String s) {
        String result = "";
        String[] arr = s.split(" ");
        if (arr.length > 1) {
            if (arr[1].matches("[+-]*")) {
                result = Integer.toString(doMath(arr));
            } else {
                if (info.isEmpty()) result = "invalidExpression";
            }
        }
        return result;
    }

    private int isInputValid(String input) {
        boolean isStringValid = input.matches("^[a-zA-Z]*[=][-+]?[0-9]*$");
        if (isStringValid) {
            return 1; //all good
        } else {
            if (!variablesArray[0].trim().matches("[a-zA-Z]*")
                    && variablesArray.length > 1 && variablesArray[1].matches("[-+]?[0-9]*")) {
                //identifier is inValid
                return 0;
            } else if (input.matches("^[a-zA-Z]*[=][a-zA-Z]*$")) {
                return 2;
            } else {
                //assignment is inValid
                return -1;
            }
        }
    }

    private String trimVariablesAndToArray(String input) {
        String inputTrimmed = input.replaceAll(" ", "");
        variablesArray = inputTrimmed.split("=");
        return inputTrimmed;
    }

    private void addVariable(String[] variables) {
        map.put(variables[0], Integer.parseInt(variables[1]));
        app.setMap(map);
    }

    private String assignNumToVariables(String[] arr) {
        List<String> arr2 = new ArrayList<>();
        String s = "";

        try {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].matches("[a-zA-Z]*")) {
                    arr2.add(i, app.getMap().get(arr[i]).toString());
                } else if (arr.length == 1) {
                    arr2.add(i, app.getMap().get(arr[i]).toString());
                } else {
                    arr2.add(i, arr[i]);
                }
            }
            for (String i : arr2) {
                s += i + " ";
            }
        } catch (NullPointerException e) {
            s = "Unknown variable";
        }
        return s;
    }

    private String newValidation(String input) {
        String trimmed = trimVariablesAndToArray(input);
        boolean reg = trimmed.matches("[^=]*");
        int ver = isInputValid(trimmed);
        String[] array = trimmed.split("=");
        String res = "";
        if (reg && ver != 2) {
            res = assignNumToVariables(input.split(" "));
        } else {
            if (ver == 1) {
                addVariable(variablesArray);
                res = "";
            } else if (ver == 2) {
                app.getMap().put(array[0], app.getMap().get(array[1]));
            } else {
                String validOutput = ver == 0 ? "Invalid identifier" : "Invalid assignment";
                res = validOutput;
            }
        }
        return res;
    }
}
