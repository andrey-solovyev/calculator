package com.surf_test.calculator.data.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class HistoryOfComputing {
    /** Поле id */
    @NotNull
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    /** Поле originalExpression, хранит в себе исходное выражение */
    @NotNull
    @NotBlank
    private String originalExpression;

    /** Поле created, хранит дату сохранения выражения в бд */
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;

    /** Поле result, хранит результат вычисления */
    @Column
    private double result;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param originalExpression - выражение
     * @param result - результат вычисления
     * @see HistoryOfComputing()
     */
    public HistoryOfComputing(@NotNull @NotBlank String originalExpression, double result) {
        this.originalExpression = originalExpression;
        this.result = result;
    }

    /**
     * PrePersist аннотация,вызываемая во всех действиях связанных с сохранением данных
     */
    @PrePersist
    void onCreate(){
        this.setCreated(LocalDateTime.now());
    }

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
