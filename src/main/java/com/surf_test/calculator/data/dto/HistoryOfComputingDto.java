package com.surf_test.calculator.data.dto;


/**
 * Создаем dto с нужными нам полями, а именно:id, выражение, результат
 */
public class HistoryOfComputingDto {
    /**
     * Поле id
     */
    private String id;
    /**
     * Поле originalExpression, хранит в себе исходное выражение
     */
    private String originalExpression;

    /**
     * Поле result, хранит результат вычисления
     */
    private double result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalExpression() {
        return originalExpression;
    }

    public void setOriginalExpression(String originalExpression) {
        this.originalExpression = originalExpression;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
