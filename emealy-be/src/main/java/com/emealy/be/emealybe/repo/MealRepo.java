package com.emealy.be.emealybe.repo;

import com.emealy.be.emealybe.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface MealRepo extends JpaRepository<Meal, String> {

}
