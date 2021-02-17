package com.surf_test.calculator.calculations;


import java.util.Arrays;

import static java.lang.Double.parseDouble;

public class Calculation {

    /**
     * Написан алгоритм для вычисления базовых выражений
     *
     * @param expression - выражение
     * @return double - возвращает результат вычисления
     */

    private String expression;
    private int pos;

    public Calculation(String expression) {
        this.expression = expression + "$";
        this.pos = 0;
    }

    public double start() {
        return add();
    }

    private String current() {
        return expression.split("")[pos].replaceAll("\\s+", "");
    }

    private double num() {
        String tmp = "";
        while (isNumeric(current()) || current() == ".") {
            tmp += current();
            pos++;
        }
        return parseDouble(tmp);
    }

    private double group() {
        if (current().equals("(")) {
            pos++;
            double res = add();
            pos++;
            return res;
        } else {
            return num();
        }
    }

    private double mult() {
        double res = group();
        while (Arrays.stream(new String[]{"*", "/"}).anyMatch(current()::equals)) {
            String op = current();
            pos++;
            double tmp = group();
            res = op.equals("*") ? res * tmp : res / tmp;
        }
        return res;
    }

    private double add() {
        double res = mult();
        while (Arrays.stream(new String[]{"+", "-"}).anyMatch(current()::equals)) {
            String op = current();
            pos++;
            double tmp = mult();
            res = op.equals("+") ? res + tmp : res - tmp;
        }
        return res;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
