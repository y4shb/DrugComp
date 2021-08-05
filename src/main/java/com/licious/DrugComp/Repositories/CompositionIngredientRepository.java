package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import com.licious.DrugComp.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionIngredientRepository extends JpaRepository {
    List<CompositionIngredient> findAll();
    List<CompositionIngredient> findAllByIngredientStrengthUnit(Ingredient ingredient, Float strength, String unit);
    List<CompositionIngredient> findAllByIngredientStrengthUnitRx(Ingredient ingredient, Float strength, String unit, Boolean rxRequired);
    List<CompositionIngredient> findAllByIngredient(Ingredient ingredient);
    List<CompositionIngredient> findByComposition(Composition composition);

}
