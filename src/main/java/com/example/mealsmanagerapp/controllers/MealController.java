package com.example.mealsmanagerapp.controllers;

import com.example.mealsmanagerapp.models.Meal;
import com.example.mealsmanagerapp.models.User;
import com.example.mealsmanagerapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    private MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping(value = "/add")
    public String createMeal(@RequestBody Meal meal) {
        mealService.save(meal);
        return "redirect:/dashboard.html";
    }

    @PutMapping
    public Meal update(@RequestBody Meal meal) {
        meal.setName(meal.getName());
        meal.setRecipe(meal.getRecipe());
        meal.setCalories(meal.getCalories());
        meal.setCookDuration(meal.getCookDuration());
        meal.setPhoto(meal.getPhoto());
        Meal updatedMeal = mealService.save(meal);
        return updatedMeal;
    }

    @GetMapping
    public void delete(@RequestParam Long id) {
        mealService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Meal> findMealById(@PathVariable("id") Long id) {
        return mealService.findMealById(id);
    }

    @GetMapping("/{name}")
    public Optional<Meal> findMealByName(@PathVariable("name") String name) {
        return mealService.findMealByName(name);
    }

    @GetMapping("/all")
    public List<Meal> findAll() {
        return mealService.getAll();
    }
}
