package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.CompositionIngredientRepository;
import com.licious.DrugComp.Repositories.CompositionRepository;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionIngredientService {
    @Autowired
    private CompositionIngredientRepository compositionIngredientRepository;
    @Autowired
    private CompositionRepository compositionRepository;

    public List<CompositionIngredient> getCompositionIngredientByIngredient(int ingredientId) {
        return compositionIngredientRepository.findAllByIngredient(ingredientId);
    }
    public List<CompositionIngredient> getCompositionIngredientByISU(int ingredientId,Float strength, String unit) {
        return compositionIngredientRepository.findAllByIngredientStrengthUnit(ingredientId, strength, unit);
    }
    public List<CompositionIngredient> getCompositionIngredientByISURx(int ingredientID, Float strength, String unit, Boolean rxRequired) {
        return compositionIngredientRepository.findAllByIngredientStrengthUnitRx(ingredientID, strength, unit, rxRequired);
    }

    // CUSTOM SERVICE METHODS


}
