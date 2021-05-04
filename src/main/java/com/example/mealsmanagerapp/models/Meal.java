package com.example.mealsmanagerapp.models;

import javax.persistence.*;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="recipe", columnDefinition = "TEXT")
    private String recipe;
    private int calories;
    private int cookDuration;
    @Column(length = 64)
    private String photo;

    public Meal() {
    }

    public Meal(String name, String recipe, int calories, int cookDuration, String photo) {
        this.name = name;
        this.recipe = recipe;
        this.calories = calories;
        this.cookDuration = cookDuration;
        this.photo = photo;
    }

    public Meal(Long id, String name, String recipe, int calories, int cookDuration, String photo) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.calories = calories;
        this.cookDuration = cookDuration;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCookDuration() {
        return cookDuration;
    }

    public void setCookDuration(int cookDuration) {
        this.cookDuration = cookDuration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Transient
    public String getPhotoImagePath() {
        if (photo == null || id == null) return null;

        return "/meal-photos/" + id + "/" + photo;
    }
}
