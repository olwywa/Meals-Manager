package com.example.mealsmanagerapp.controllers;

import com.example.mealsmanagerapp.models.Meal;
import com.example.mealsmanagerapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @PutMapping(path = "/edit/{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) String recipe,
                       @RequestParam(required = false) int calories,
                       @RequestParam(required = false) int cookDuration,
                       @RequestParam(required = false) String photo) {
        mealService.update(id, name, recipe, calories, cookDuration, photo);
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        mealService.delete(id);
        return "redirect:/loggedDashboard";
    }

    @GetMapping("/{id}")
    public Optional<Meal> findMealById(@PathVariable("id") Long id) {
        return mealService.findMealById(id);
    }

    @GetMapping("/{name}")
    public Optional<Meal> findMealByName(@PathVariable("name") String name) {
        return mealService.findMealByName(name);
    }

}
