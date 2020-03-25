package org.example.utils;

import java.math.BigInteger;

public class MyConditions {

    public static boolean isBigNumber(String i) {
        int a = new BigInteger(i).compareTo(BigInteger.valueOf(Integer.MAX_VALUE));
        int b = new BigInteger(i).compareTo(BigInteger.valueOf(Integer.MIN_VALUE));

        return (a > 0) || (b < 0);
    }
}