package com.example.mealsmanagerapp.services;

import com.example.mealsmanagerapp.models.Meal;
import com.example.mealsmanagerapp.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAll() {
        return mealRepository.findAll();
    }

    public Optional<Meal> findMealByName(String name) {
        return mealRepository.findByName(name);
    }

    public Optional<Meal> findMealById(Long id) {
        return mealRepository.findById(id);
    }

    public Meal save(Meal meal){
        return mealRepository.save(meal);
    }

    public void delete(Long id){
        mealRepository.deleteById(id);
    }
}
