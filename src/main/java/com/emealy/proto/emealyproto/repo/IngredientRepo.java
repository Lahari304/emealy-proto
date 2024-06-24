package com.emealy.proto.emealyproto.repo;

import com.emealy.proto.emealyproto.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Component
public interface IngredientRepo extends JpaRepository<Ingredient, UUID> {

    @Query("select id from Ingredient where name = ?1")
    UUID getIngredientIdByName(String name);
}
