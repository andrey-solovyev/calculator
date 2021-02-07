package com.surf_test.calculator.calculations;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculation {

    /**
     * Для вычисления выражений использовался: Двухстековый алгоритм Дейкстры для вычисления выражений
     *
     * @param expression - выражение
     * @return double - возвращает результат вычисления
     */
//    public static double calculate(String expression) {
//        Queue<String> queue = new LinkedList<>();
//        queue.addAll(Arrays.asList(("(" + expression + ")").split("")));
//        Stack<String> ops = new Stack<String>();
//        Stack<Double> vals = new Stack<Double>();
//        while (!queue.isEmpty()) {
//            String mark = queue.poll();
//            if (mark.equals("(")) ;
//            else if (mark.equals("+")) ops.push(mark);
//            else if (mark.equals("-")) ops.push(mark);
//            else if (mark.equals("*")) ops.push(mark);
//            else if (mark.equals("/")) ops.push(mark);
//            else if (mark.equals("sqrt")) ops.push(mark);
//            else if (mark.equals("^")) ops.push(mark);
//            else if (mark.equals(")")) {
//                double v = vals.pop();
//                String op = ops.pop();
//                if (op.equals("+")) v = vals.pop() + v;
//                else if (op.equals("-")) v = vals.pop() - v;
//                else if (op.equals("*")) v = vals.pop() * v;
//                else if (op.equals("/")) v = vals.pop() / v;
//                else if (op.equals("sqrt")) v = Math.sqrt(v);
//                else if (op.equals("^")) v = Math.pow(v, vals.pop());
//                vals.push(v);
//            } else {
//                vals.push(Double.parseDouble(mark));
//            }
//        }
//        return vals.pop();
//    }
    public static double calculate(String expression) {
        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(expression).getValue(Double.class);
    }
}
