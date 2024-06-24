package com.emealy.proto.emealyproto.controller;

import com.emealy.proto.emealyproto.model.Meal;
import com.emealy.proto.emealyproto.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MealController {

    @Autowired
    MealService mealService;

    @RequestMapping({"/", "home"})
    public String home(){
        return "home";
    }

    @PostMapping("addMeal")
    public Meal addMeal(@RequestBody Meal meal){
        mealService.addMeal(meal);
        return mealService.findMeal(meal.getName());
    }
    @RequestMapping("allMeals")
    public List<Meal> getMeals(){
        return mealService.getAllMeals();
    }

    @RequestMapping("getMeal")
    public Meal getMeal(@RequestParam String name){
        return mealService.getMeal(name);
    }

    @PutMapping("editMeal")
    public Meal editMeal(@RequestParam String name, @RequestBody Meal meal){
        return mealService.editMeal(name, meal);
    }
}
