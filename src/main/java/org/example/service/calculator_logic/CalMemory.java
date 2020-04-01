package org.example.service.calculator_logic;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class CalMemory {

    private static Map<String, Integer> memory = new HashMap<>();
    private static Map<String, BigInteger> bigMemory = new HashMap<>();

    public void addToMemory(String identifier, int assignment) {
        memory.put(identifier, assignment);
    }

    public int getNumber(String key) {
        return memory.get(key);
    }

    public void addToBigMemory(String identifier, BigInteger assignment) {
        bigMemory.put(identifier, assignment);
    }

    public BigInteger getBigNumber(String key) {
        return bigMemory.get(key);
    }
}