package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.CompositionIngredientRepository;
import com.licious.DrugComp.Repositories.CompositionRepository;
import com.licious.DrugComp.Repositories.IngredientRepository;
import com.licious.DrugComp.Repositories.MoleculeRepository;
import com.licious.DrugComp.models.*;
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
    @Autowired
    private MoleculeRepository moleculeRepository;


    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findOneByName(name);
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

    // To Get Compositions containing given Ingredient of given strength and molecule with given rxRequired.
    public List<CompositionIngredient> getCompositionsByIngredientNameStrengthUnitRx(String ingredientName, float strength, String unit, Boolean rxRequired) {
        //fetch ingredientId from ingredientName
        int ingredientId = ingredientRepository.findOneByName(ingredientName).getId();
        List<CompositionIngredient> compositionIngredientListTEMP = compositionIngredientRepository.findByIngredientAndStrengthAndUnit(ingredientId, strength, unit);
        //list to return :
        List<CompositionIngredient> compositionIngredients = new ArrayList<>();
        for(CompositionIngredient c : compositionIngredientListTEMP) {
            int ingrId = c.getIngredient().getId();
            List<MoleculeIngredient> moleculeIngredientList = moleculeIngredientService.getMoleculeIngredientByIngredientId(ingrId);
            List<Integer> moleculeIds = new ArrayList<>();
            for (MoleculeIngredient mi : moleculeIngredientList) {
                 moleculeIds.add(mi.getMolecule().getId());
            }
            List<Boolean> moleculeRxList = new ArrayList<>();
            for (Integer i : moleculeIds){
                moleculeRxList.add(moleculeService.getMoleculeById(i).getRxRequired());
            }
            //iterate through Boolean List moleculeRxList and if all values are == rxRequired, compositionIngredients.add(c);
            int flag = 0;
            for(Boolean rx : moleculeRxList) {
                if(rx == rxRequired)
                    flag = 1;
                else
                    flag = 0;
            }
            if (flag == 1)
                compositionIngredients.add(c);
        }
        return compositionIngredients;


    }

    /*UNDER PROGRESS*/
    /*
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
    */

}
