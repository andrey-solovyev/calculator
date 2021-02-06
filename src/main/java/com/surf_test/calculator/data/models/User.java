package com.surf_test.calculator.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_table")
public class User {
    /**
     * Поле id
     */
    @NotNull
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(insertable = false, updatable = false)
    private String userId;
    /**
     * Поле name, хранит в себе имя человека
     */
    @NotNull
    @NotBlank
    private String name;
    /**
     * Поле name, хранит в себе фамилию человека
     */
    @NotNull
    @NotBlank
    private String surname;

    @OneToMany(mappedBy = "owner")
    private List<HistoryOfComputing> historyOfComputings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
