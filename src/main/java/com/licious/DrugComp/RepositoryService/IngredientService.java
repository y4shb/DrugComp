package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.CompositionIngredientRepository;
import com.licious.DrugComp.Repositories.CompositionRepository;
import com.licious.DrugComp.Repositories.IngredientRepository;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import com.licious.DrugComp.models.Ingredient;
import com.licious.DrugComp.models.MoleculeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private CompositionRepository compositionRepository;
    @Autowired
    private CompositionIngredientRepository compositionIngredientRepository;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private MoleculeIngredientService moleculeIngredientService;
    @Autowired
    private MoleculeService moleculeService;


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
    public List<CompositionIngredient> getCompositionsByIngredientNameStrengthUnit(String ingredientName, float strength, String unit) {
        int ingredientId = ingredientService.getIngredientByName(ingredientName).getId();
        return compositionIngredientRepository.findByIngredientAndStrengthAndUnit(ingredientId, strength, unit);
    }

    public List<Composition> getCompositionsByIngredientIdStrengthUnit(int ingredientId, float strength, String unit) {
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findByIngredientAndStrengthAndUnit(ingredientId, strength, unit);
        //we have all composition_ingredients with given ingredient, strength, unit
        //now, get list of compositions corresponding to compId in composition_ingredients
        List<Composition> compositionListTEMP = new ArrayList<>();
        for(CompositionIngredient c : compositionIngredientList) {
            compositionListTEMP.add(c.getComposition());
        }
        //ensure unique compositions
        List<Composition> compositionList = compositionListTEMP.stream().distinct().collect(Collectors.toList());
        return compositionList;
    }
    //Third API

    public List<Composition> getCompositionsByIngredientNameStrengthUnitRx(String ingredientName, float strength, String unit, Boolean rxRequired) {
        int ingredientId = ingredientService.getIngredientByName(ingredientName).getId();
        List<CompositionIngredient> compositionIngredientList =  compositionIngredientRepository.findByIngredientAndStrengthAndUnit(ingredientId, strength, unit);
        List<Integer> ingredientIdList = new ArrayList<>();
        for(CompositionIngredient c : compositionIngredientList) {
            ingredientIdList.add(c.getIngredient().getId());
        }
        List<MoleculeIngredient> moleculeIngredientList = new ArrayList<>();
        for(int i : ingredientIdList) {
             List<MoleculeIngredient> temp = moleculeIngredientService.getMoleculeIngredientByIngredientId(i);
             moleculeIngredientList.addAll(temp);
        }
        List<Boolean> rxList = new ArrayList<>();
        for(MoleculeIngredient mi : moleculeIngredientList) {
            //BELOW IS LIST OF RXREQUIRED. IF THIS IS EQUAL TO INPUT RX VALUE : ONLY THEN SHOW THAT COMPOSITION
            //USE FOR LOOPS IN NESTED IF STATEMENTS - LOGIC FOR FILTERING COMPOSITIONS ON THIS BASIS NEEDED.
            rxList.add(moleculeService.getMoleculeById(mi.getMolecule().getId()).getRxRequired());
        }
        //filter out above based on RX
        List<Composition> compositionListTEMP = new ArrayList<>();
        for(CompositionIngredient c : compositionIngredientList) {
            compositionListTEMP.add(c.getComposition());
        }

        List<Composition> compositionList = compositionListTEMP.stream().distinct().collect(Collectors.toList());
        return compositionList;
    }
    public List<Composition> getCompositionsByIngredientIdStrengthUnitRx(int ingredientId, float strength, String unit, Boolean rxRequired) {
        List<CompositionIngredient> compositionIngredientList =  compositionIngredientRepository.findByIngredientAndStrengthAndUnit(ingredientId, strength, unit);
        //filter out above based on RX
        List<Composition> compositionListTEMP = new ArrayList<>();
        for(CompositionIngredient c : compositionIngredientList) {
            compositionListTEMP.add(c.getComposition());
        }

        List<Composition> compositionList = compositionListTEMP.stream().distinct().collect(Collectors.toList());
        return compositionList;
    }


}
