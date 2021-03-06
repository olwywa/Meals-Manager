package com.example.mealsmanagerapp.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Meal> meals;

    public User() {
    }

    public User(Long id, String name, String email, String password, List<Meal> meals) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.meals = meals;
    }

    public User(String name, String email, String password, List<Meal> meals) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.meals = meals;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String nickname) {
        this.email = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}