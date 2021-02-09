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
    private String id;
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


    /**
     * Для нахождения всех вычислений человеком
     */
    @NotNull
    @NotBlank
    private String password;
    @OneToMany(mappedBy = "owner")
    private List<HistoryOfComputing> historyOfComputings;


    @ManyToMany()
    @JoinTable(
            name = "role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private List<UserRole> userRoles;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<HistoryOfComputing> getHistoryOfComputings() {
        return historyOfComputings;
    }

    public void setHistoryOfComputings(List<HistoryOfComputing> historyOfComputings) {
        this.historyOfComputings = historyOfComputings;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
