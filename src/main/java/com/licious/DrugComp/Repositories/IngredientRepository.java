package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findAll();
    Ingredient findById(int id);
    Ingredient findByName(String name);
}
