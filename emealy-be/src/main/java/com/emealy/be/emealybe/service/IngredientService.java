package com.emealy.be.emealybe.service;

import com.emealy.be.emealybe.model.Ingredient;
import com.emealy.be.emealybe.model.IngredientsList;
import com.emealy.be.emealybe.repo.IngredientRepo;
import com.emealy.be.emealybe.repo.IngredientsListRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class IngredientService {
    @Autowired
    IngredientsListRepo ingredientsListRepo;
    @Autowired
    IngredientRepo ingredientRepo;
    Logger logger = LogManager.getLogger(IngredientService.class);

    public List<String> addIngredients(String mealName, List<String> ingredients){

        for(String ingredient:ingredients){
            if(!checkForIngredient(mealName, ingredient)) {
                UUID ingId = getIngredientIdByName(ingredient);
                ingredientsListRepo.saveAndFlush(new IngredientsList(mealName, ingId));
            }
            else logger.debug("Ingredient is already present. Ignoring..");
        }

        return getMealIngredients(mealName);
    }

    public List<String> removeIngredients(String mealName, List<String> ingredients){
        for(String ingredient:ingredients){
            if(checkForIngredient(mealName, ingredient)){
                UUID ingId = getIngredientIdByName(ingredient);
                ingredientsListRepo.deleteById(mealName+ingId.toString());
            }
            else logger.debug("Ingredient not found. Try again.");
        }

        return getMealIngredients(mealName);
    }

    boolean checkForIngredient(String mealName, String ingredient){
        return ingredientsListRepo.existsById(mealName+ingredient);
    }

    UUID getIngredientIdByName(String name){
        if(ingredientRepo.getIngredientIdByName(name) == null) {
            ingredientRepo.save(new Ingredient(name));
        }
        return ingredientRepo.getIngredientIdByName(name);
    }

    public List<String> getMealIngredients(String mealName){
        List<UUID> ingIds = ingredientsListRepo.getMealIngredientIds(mealName);
        return ingredientRepo.getIngredientNameById(ingIds);
    }
}
