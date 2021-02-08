package com.surf_test.calculator.data.dto;

import java.util.Objects;

public class HistoryOfComputingDtoWithUser {
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

    private UserDto userDto;

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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryOfComputingDtoWithUser that = (HistoryOfComputingDtoWithUser) o;
        return Double.compare(that.result, result) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(originalExpression, that.originalExpression) &&
                Objects.equals(userDto, that.userDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalExpression, result, userDto);
    }
}
