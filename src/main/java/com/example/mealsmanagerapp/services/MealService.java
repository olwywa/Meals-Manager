package com.example.mealsmanagerapp.services;

import com.example.mealsmanagerapp.models.Meal;
import com.example.mealsmanagerapp.models.User;
import com.example.mealsmanagerapp.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    public Meal update(Long id, String name, String recipe, int calories, int cookDuration, String photo){

        Meal mealExists = mealRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Meal with id" + id + "does not exists"));

        if(name != null && name.length() > 0 && !Objects.equals(mealExists.getName(), name)) {
            mealExists.setName(name);
        }

        if(recipe != null && recipe.length() > 0 && !Objects.equals(mealExists.getRecipe(), recipe)) {
            mealExists.setRecipe(recipe);
        }

        if(calories >= 0 && !Objects.equals(mealExists.getCalories(), calories)) {
            mealExists.setCalories(calories);
        }

        if(cookDuration >= 0 && !Objects.equals(mealExists.getCookDuration(), cookDuration)) {
            mealExists.setCookDuration(cookDuration);
        }

        if(photo != null) {
            mealExists.setPhoto(photo);
        }

        mealRepository.save(mealExists);
        return mealExists;
    }

    public Meal save(Meal meal){
        return mealRepository.save(meal);
    }

    public void delete(Long id){
        mealRepository.deleteById(id);
    }
}
