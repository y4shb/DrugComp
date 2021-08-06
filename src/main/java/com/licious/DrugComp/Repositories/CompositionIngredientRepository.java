package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import com.licious.DrugComp.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompositionIngredientRepository extends JpaRepository<CompositionIngredient, Integer> {
    //List<CompositionIngredient> findAll();
    @Query(value="SELECT * FROM composition_ingredients WHERE ingredient_id=?1 AND strength=?2 AND unit=?3", nativeQuery = true)
    List<CompositionIngredient> findByIngredientAndStrengthAndUnit(int ingredientId, float strength, String unit);

    List<CompositionIngredient> findByIngredient(Ingredient ingredient);
    List<CompositionIngredient> findByComposition(Composition composition);

}
