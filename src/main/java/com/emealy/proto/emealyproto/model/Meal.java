package com.emealy.proto.emealyproto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Meal {

    @Id
    String name;
    @Enumerated(EnumType.STRING)
    MealType mealType;
//    String process;
    public Meal() {}
    public Meal(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public MealType getMealType() {
        return mealType;
    }


}
