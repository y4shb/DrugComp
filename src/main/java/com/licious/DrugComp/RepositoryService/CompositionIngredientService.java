package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.CompositionIngredientRepository;
import com.licious.DrugComp.Repositories.CompositionRepository;
import com.licious.DrugComp.Repositories.MoleculeIngredientRepository;
import com.licious.DrugComp.Repositories.MoleculeRepository;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import com.licious.DrugComp.models.Ingredient;
import com.licious.DrugComp.models.MoleculeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompositionIngredientService {
    @Autowired
    private CompositionIngredientRepository compositionIngredientRepository;
    @Autowired
    private CompositionRepository compositionRepository;
    @Autowired
    private CompositionService compositionService;
    @Autowired
    private MoleculeRepository moleculeRepository;
    @Autowired
    private MoleculeIngredientRepository moleculeIngredientRepository;

    public List<CompositionIngredient> getCompositionIngredientByIngredient(Ingredient ingredient) {
        return compositionIngredientRepository.findAllByIngredient(ingredient);
    }
    public List<CompositionIngredient> getCompositionIngredientByISU(Ingredient ingredientId, Float strength, String unit) {
        return compositionIngredientRepository.findAllByIngredientStrengthUnit(ingredientId, strength, unit);
    }
    public List<CompositionIngredient> getCompositionIngredientByISURx(Ingredient ingredientID, Float strength, String unit, Boolean rxRequired) {
        return compositionIngredientRepository.findAllByIngredientStrengthUnitRx(ingredientID, strength, unit, rxRequired);
    }

    // CUSTOM SERVICE METHODS
    public void getCompositionDetailsById(int compId) {
        Composition composition = compositionService.getCompositionById(compId);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findByComposition(composition);
        //******display 'compositionIngredientList'
        //iterate
        List<Ingredient> ingredients = null;
        List<Integer> ingredientIdList = new ArrayList<>();
        List<MoleculeIngredient> moleculeIngredientList = null;
        for(CompositionIngredient i : compositionIngredientList) {
            //fetch Ingredients
            ingredients.add(i.getIngredient());
            //fetch ingredientIds
            ingredientIdList.add(i.getIngredient().getId());
        }
        //******display 'ingredients'
        //find molecule_ingredients with ingredientIds in ingredientIdList
        for(Integer ingredientId : ingredientIdList) {
            moleculeIngredientList.addAll(moleculeIngredientRepository.findAllByIngredientId(ingredientId));

        }


    }



}
