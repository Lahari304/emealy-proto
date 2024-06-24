package com.emealy.proto.emealyproto.service;

import com.emealy.proto.emealyproto.model.Meal;
import com.emealy.proto.emealyproto.repo.MealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MealService {

    @Autowired
    MealRepo mealRepo;

    public List<Meal> getAllMeals(){
        return mealRepo.findAll();
    }

    public Meal getMeal(String name){
        Optional<Meal> meal = mealRepo.findById(name);
        return meal.isPresent() ? meal.get() : new Meal("Meal doesn't exist");
    }

    public void addMeal(Meal meal){
        Meal fmeal = getMeal(meal.getName());
        if(!"Meal doesn't exist".equals(fmeal.getName())) {
            System.out.println("Meal with name "+meal.getName()+" already exists. Check again");
            return;
        }
        mealRepo.save(meal);
    }

    void removeMeal(Meal meal){
        mealRepo.delete(meal);
    }

    public Meal findMeal(String name){
        return mealRepo.getReferenceById(name);
    }

    public Meal editMeal(String name, Meal meal){
        Meal fmeal = findMeal(name);

        if(null == fmeal) System.out.println("Meal doesn't exist.. adding as new meal");
        else removeMeal(meal);

        addMeal(meal);
        return findMeal(meal.getName());
    }
}
