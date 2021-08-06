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
    @Query(name = "SELECT DISTINCT composition_ingredients.composition_id FROM compositions"
            +" INNER JOIN composition_ingredients ON compositions.id=composition_ingredients.composition_id"
            +" INNER JOIN molecule_ingredients ON composition_ingredients.ingredient_id=molecule_ingredients.ingredient_id"
            +" INNER JOIN molecules ON molecule_ingredients.molecule_id=molecules.id"
            +" WHERE(molecule_ingredients.ingredient_id=?1 and strength=?2 and unit=?3 and molecules.rx_required=?4);")
    List<CompositionIngredient> findByIngredientAndStrengthAndUnitAndRx(int ingredientId, float strength, String unit, Boolean rxRequired);
    List<CompositionIngredient> findByIngredient(Ingredient ingredient);
    List<CompositionIngredient> findByComposition(Composition composition);

}
