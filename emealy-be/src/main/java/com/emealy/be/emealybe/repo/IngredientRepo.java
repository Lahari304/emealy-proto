package com.emealy.be.emealybe.repo;

import com.emealy.be.emealybe.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Component
public interface IngredientRepo extends JpaRepository<Ingredient, UUID> {

    @Query("select id from Ingredient where name = ?1")
    UUID getIngredientIdByName(String name);

    @Query("select name from Ingredient where id in ?1")
    List<String> getIngredientNameById(List<UUID> ids);
}
