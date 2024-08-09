package com.emealy.be.emealybe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class IngredientsList {

    @Id
    String pk; // mealname + ingredientId
    String mealName;
    UUID ingredientId;

    // TODO add quantity parameter
    public IngredientsList() {

    }
//
    public IngredientsList(String mealname, UUID ingredientId) {
        this.ingredientId = ingredientId;
        this.mealName = mealname;
        this.pk = mealname+ingredientId.toString();
    }
}
