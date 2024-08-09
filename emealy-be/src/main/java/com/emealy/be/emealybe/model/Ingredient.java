package com.emealy.be.emealybe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    UUID id;
    String name;

    public Ingredient(String name){
        this.name = name;
    }

    protected Ingredient(){}
}
