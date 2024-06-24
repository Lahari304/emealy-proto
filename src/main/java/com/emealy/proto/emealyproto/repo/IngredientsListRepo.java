package com.emealy.proto.emealyproto.repo;

import com.emealy.proto.emealyproto.model.IngredientsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Component
public interface IngredientsListRepo extends JpaRepository<IngredientsList, String> {

    @Query("select ingredientId from IngredientsList where mealName = ?1")
    List<UUID> getMealIngredientIds(String mealName);

}
