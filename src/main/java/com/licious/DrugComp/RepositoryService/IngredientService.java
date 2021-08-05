package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.CompositionIngredientRepository;
import com.licious.DrugComp.Repositories.CompositionRepository;
import com.licious.DrugComp.Repositories.IngredientRepository;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import com.licious.DrugComp.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private CompositionRepository compositionRepository;
    @Autowired
    private CompositionIngredientRepository compositionIngredientRepository;



    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }


    /* CUSTOM SERVICE METHODS */
    //Second API
    public List<Composition> getCompositionsByIngredientNameStrengthUnit(String ingredientName, Float strength, String unit) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientName);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findAllByIngredientStrengthUnit(ingredient, strength, unit);
        //we have all composition_ingredients with given ingredient, strength, unit
        //now, get list of compositions corresponding to compId in composition_ingredients
        List<Composition> compositionListTEMP = null;
        for(CompositionIngredient c : compositionIngredientList) {
            compositionListTEMP.add(c.getComposition());
        }
        //ensure unique compositions
        List<Composition> compositionList = compositionListTEMP.stream().distinct().collect(Collectors.toList());
        return compositionList;
    }

    public List<Composition> getCompositionsByIngredientIdStrengthUnit(int ingredientId, Float strength, String unit) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findAllByIngredientStrengthUnit(ingredient, strength, unit);
        //we have all composition_ingredients with given ingredient, strength, unit
        //now, get list of compositions corresponding to compId in composition_ingredients
        List<Composition> compositionListTEMP = null;
        for(CompositionIngredient c : compositionIngredientList) {
            compositionListTEMP.add(c.getComposition());
        }
        //ensure unique compositions
        List<Composition> compositionList = compositionListTEMP.stream().distinct().collect(Collectors.toList());
        return compositionList;
    }
    public List<Composition> getCompositionsByIngredientNameStrengthUnitRx(String ingredientName, Float strength, String unit, Boolean rxRequired) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientName);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findAllByIngredientStrengthUnitRx(ingredient, strength, unit, rxRequired);
        //we have all composition_ingredients with given ingredient, strength, unit, rx
        //now, get list of compositions corresponding to compId in composition_ingredients
        List<Composition> compositionListTEMP = null;
        for(CompositionIngredient c : compositionIngredientList) {
            compositionListTEMP.add(c.getComposition());
        }
        //ensure unique compositions
        List<Composition> compositionList = compositionListTEMP.stream().distinct().collect(Collectors.toList());
        return compositionList;
    }
    public List<Composition> getCompositionsByIngredientIdStrengthUnitRx(int ingredientId, Float strength, String unit, Boolean rxRequired) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findAllByIngredientStrengthUnitRx(ingredient, strength, unit, rxRequired);
        //we have all composition_ingredients with given ingredient, strength, unit, rx
        //now, get list of compositions corresponding to compId in composition_ingredients
        List<Composition> compositionListTEMP = null;
        for(CompositionIngredient c : compositionIngredientList) {
            compositionListTEMP.add(c.getComposition());
        }
        //ensure unique compositions
        List<Composition> compositionList = compositionListTEMP.stream().distinct().collect(Collectors.toList());
        return compositionList;
    }

}
