package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.*;
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



    //private CompositionResponse compositionResponse;

    public List<CompositionIngredient> getCompositionIngredientByIngredient(Ingredient ingredient) {
        return compositionIngredientRepository.findByIngredient(ingredient);
    }
    public List<CompositionIngredient> getCompositionIngredientByISU(String ingredientName, float strength, String unit) {
        IngredientService ingredientService = new IngredientService();
        int ingredientId = ingredientService.getIngredientByName(ingredientName).getId();
        return compositionIngredientRepository.findByIngredientAndStrengthAndUnit(ingredientId, strength, unit);
    }


    /* CUSTOM SERVICE METHODS */
    //First API
    public CompositionResponse getCompositionDetailsById(int compId) {
        Composition composition = compositionService.getCompositionById(compId);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findByComposition(composition);
        //******display 'compositionIngredientList'
        CompositionResponse compositionResponse = new CompositionResponse();
        compositionResponse.setCompositionIngredientList(compositionIngredientList);
        //iterate
        List<Ingredient> ingredients = new ArrayList<>();
        List<Integer> ingredientIdList = new ArrayList<>();
        List<MoleculeIngredient> moleculeIngredientList = new ArrayList<>();
        List<Integer> moleculeIdListTEMP = new ArrayList<>();
        for(CompositionIngredient i : compositionIngredientList) {
            //fetch Ingredients
            ingredients.add(i.getIngredient());
            //fetch ingredientIds
            ingredientIdList.add(i.getIngredient().getId());
        }
        //******display 'ingredients'
        compositionResponse.setIngredients(ingredients);
        //find molecule_ingredients with ingredientIds in ingredientIdList
        for(Integer ingredientId : ingredientIdList) {
            moleculeIngredientList.addAll(moleculeIngredientRepository.findByIngredientId(ingredientId));

        }
        //fetch moleculeIds from moleculeIngredients
        for(MoleculeIngredient moleculeIngredient : moleculeIngredientList) {
            moleculeIdListTEMP.add(moleculeIngredient.getMolecule().getId());
        }
        //get distinct moleculeIds
        List<Integer> moleculeIdList = moleculeIdListTEMP.stream().distinct().collect(Collectors.toList());
        //get molecules
        List<Molecule> moleculeList = new ArrayList<>();
        for(Integer m : moleculeIdList) {
            moleculeList.add(moleculeService.getMoleculeById(m));
        }
        //******display 'moleculeList'
        compositionResponse.setMoleculeList(moleculeList);


        return compositionResponse;

    }


    public CompositionResponse getCompositionDetailsByName(String name) {
        Composition composition = compositionService.getCompositionByName(name);
        List<CompositionIngredient> compositionIngredientList = compositionIngredientRepository.findByComposition(composition);
        //******display 'compositionIngredientList'
        CompositionResponse compositionResponse = new CompositionResponse();
        compositionResponse.setCompositionIngredientList(compositionIngredientList);
        //iterate
        List<Ingredient> ingredients = new ArrayList<>();
        List<Integer> ingredientIdList = new ArrayList<>();
        List<MoleculeIngredient> moleculeIngredientList = new ArrayList<>();
        List<Integer> moleculeIdListTEMP = new ArrayList<>();
        for (CompositionIngredient i : compositionIngredientList) {
            //fetch Ingredients
            ingredients.add(i.getIngredient());
            //fetch ingredientIds
            ingredientIdList.add(i.getIngredient().getId());
        }
        //******display 'ingredients'
        compositionResponse.setIngredients(ingredients);
        //find molecule_ingredients with ingredientIds in ingredientIdList
        for (Integer ingredientId : ingredientIdList) {
            moleculeIngredientList.addAll(moleculeIngredientRepository.findByIngredientId(ingredientId));

        }
        //fetch moleculeIds from moleculeIngredients
        for (MoleculeIngredient moleculeIngredient : moleculeIngredientList) {
            moleculeIdListTEMP.add(moleculeIngredient.getMolecule().getId());
        }
        //get distinct moleculeIds
        List<Integer> moleculeIdList = moleculeIdListTEMP.stream().distinct().collect(Collectors.toList());
        //get molecules
        List<Molecule> moleculeList = new ArrayList<>();
        for (Integer m : moleculeIdList) {
            moleculeList.add(moleculeService.getMoleculeById(m));
        }
        //******display 'moleculeList'
        compositionResponse.setMoleculeList(moleculeList);


        return compositionResponse;
    }





}
