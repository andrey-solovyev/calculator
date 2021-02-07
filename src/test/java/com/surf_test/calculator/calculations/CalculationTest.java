package com.surf_test.calculator.calculations;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    @Test
    void calculate() {
        ExpressionParser parser = new SpelExpressionParser();
        assertEquals(21, parser.parseExpression("(1+2)*(4+(5-6/3))").getValue(Double.class), 0.00001);
        assertEquals(13.0, parser.parseExpression("35/5+36/4-3").getValue(Double.class), 0.00001);
        assertEquals(44800, parser.parseExpression("560000/100*8").getValue(Double.class), 0.00001);
//        assertEquals(7.0, Calculation.calculate("(1+2)*(4+(5-6/3))"), 0.00001);
//        assertEquals(7.0, Calculation.calculate("(1+2)*(4+(5-6/3))"), 0.00001);


    }
}