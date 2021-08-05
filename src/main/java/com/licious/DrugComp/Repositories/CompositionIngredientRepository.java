package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.CompositionIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionIngredientRepository extends JpaRepository {
    List<CompositionIngredient> findAll();
    List<CompositionIngredient> findAllByIngredientStrengthUnit(int ingredientId, Float strength, String unit);
    List<CompositionIngredient> findAllByIngredientStrengthUnitRx(int ingredientId, Float strength, String unit, Boolean rxRequired);
    List<CompositionIngredient> findAllByIngredient(int ingredientId);

}
