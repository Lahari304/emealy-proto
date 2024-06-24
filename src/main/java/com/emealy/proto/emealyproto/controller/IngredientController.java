package com.emealy.proto.emealyproto.controller;

import com.emealy.proto.emealyproto.model.Ingredient;
import com.emealy.proto.emealyproto.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @PutMapping("addIngredients")
    List<Ingredient> addIngredients(@RequestParam String mealName, @RequestBody List<String> ingredients){
        return ingredientService.addIngredients(mealName, ingredients);
    }

    @PutMapping("removeIngredients")
    List<Ingredient> removeIngredients(@RequestParam String mealName, @RequestBody List<String> ingredients){
        return ingredientService.removeIngredients(mealName, ingredients);
    }

    @PostMapping("getAllIngredients")
    List<Ingredient> getAllIngredients(@RequestParam String mealName){
        return ingredientService.getMealIngredients(mealName);
    }
}
