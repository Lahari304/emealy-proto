package com.emealy.proto.emealyproto.repo;

import com.emealy.proto.emealyproto.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface MealRepo extends JpaRepository<Meal, String> {

}
