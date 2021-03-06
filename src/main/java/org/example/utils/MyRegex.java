package org.example.utils;

public class MyRegex {

    public static final String DIVIDE_STRING = "(?<=[=()/*+-])(?=[0-9()a-zA-Z])|(?<=[0-9()a-zA-Z])(?=[=()/*+-])|[ ]";
    public static final String IS_NUMBER = "^\\d*$|^[a-zA-Z]*$*";
    public static final String IS_DATE = "^(0[1-9]|[1-2][0-9]|3[0-1])/([0][0-9]|1[0-2])/\\d{2}$";
    public static final String OPERATORS = "[*/+-]";
    public static final String TEN_DIGITS_NUMBER = ".*\\d{10}.*";
    public static final String IS_NOT_REGISTERED_COMMAND = "[/][a-zA-Z]*.*?";
    public static final String IS_ASSIGNMENT = "^[a-zA-Z]*[=][+-]?[0-9]*$";
    public static final String HAS_DIGITS = ".*[0-9].*";
    public static final String HAS_MORE_THAN_0NE_ASTRIX = ".*[*]{2,}.*";
    public static final String ONLY_LETTERS = "[a-zA-Z]*";
    public static final String EMAIL_IS_VALID = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
}
