package com.surf_test.calculator.calculations;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    @Test
    void calculate() {
        assertEquals(15,new Calculation("1+2*(4+(5-6/3))").start(), 0.00001);
        assertEquals(13.0,new Calculation("35/5+36/4-3").start(), 0.00001);
        assertEquals(44800,new Calculation("560000/100*8").start(), 0.00001);
        assertEquals(15,new Calculation("1+2*(4+(5-6/3))").start(), 0.00001);
    }
}