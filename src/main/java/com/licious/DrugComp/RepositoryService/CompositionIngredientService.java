package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.CompositionIngredientRepository;
import com.licious.DrugComp.Repositories.CompositionRepository;
import com.licious.DrugComp.Repositories.MoleculeIngredientRepository;
import com.licious.DrugComp.Repositories.MoleculeRepository;
import com.licious.DrugComp.dto.CompositionResponse;
import com.licious.DrugComp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private MoleculeService moleculeService;

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
    public CompositionResponse getCompositionDetailsById(int compId) {
        Composition composition = compositionService.getCompositionById(compId);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findByComposition(composition);
        //******display 'compositionIngredientList'
        //iterate
        List<Ingredient> ingredients = null;
        List<Integer> ingredientIdList = new ArrayList<>();
        List<MoleculeIngredient> moleculeIngredientList = null;
        List<Integer> moleculeIdListTEMP = new ArrayList<>();
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
        //fetch moleculeIds from moleculeIngredients
        for(MoleculeIngredient moleculeIngredient : moleculeIngredientList) {
            moleculeIdListTEMP.add(moleculeIngredient.getMoleculeId());
        }
        //get distinct moleculeIds
        List<Integer> moleculeIdList = moleculeIdListTEMP.stream().distinct().collect(Collectors.toList());
        //get molecules
        List<Molecule> moleculeList = null;
        for(Integer m : moleculeIdList) {
            moleculeList.add(moleculeService.getMoleculeById(m));
        }
        //******display 'moleculeList'
        return null;
        /* CONVERT RETURN TO DTO TYPE ??? */
    }



}
