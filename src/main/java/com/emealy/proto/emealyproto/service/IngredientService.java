package com.emealy.proto.emealyproto.service;

import com.emealy.proto.emealyproto.model.Ingredient;
import com.emealy.proto.emealyproto.model.IngredientsList;
import com.emealy.proto.emealyproto.repo.IngredientRepo;
import com.emealy.proto.emealyproto.repo.IngredientsListRepo;
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

    public List<Ingredient> addIngredients(String mealName, List<String> ingredients){

        for(String ingredient:ingredients){
            if(!checkForIngredient(mealName, ingredient)) {
                UUID ingId = getIngredientIdByName(ingredient);
                ingredientsListRepo.saveAndFlush(new IngredientsList(mealName, ingId));
            }
            else logger.debug("Ingredient "+ingredient+" is already present. Ignoring..");
        }

        return getMealIngredients(mealName);
    }

    public List<Ingredient> removeIngredients(String mealName, List<String> ingredients){
        for(String ingredient:ingredients){
            if(checkForIngredient(mealName, ingredient)){
                UUID ingId = getIngredientIdByName(ingredient);
                ingredientsListRepo.deleteById(mealName+ingId.toString());
            }
            else logger.debug("Ingredient "+ingredient+" not found. Try again.");
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

    public List<Ingredient> getMealIngredients(String mealName){
        List<UUID> ingIds = ingredientsListRepo.getMealIngredientIds(mealName);


        return ingredientRepo.findAllById(ingIds);
    }
}
