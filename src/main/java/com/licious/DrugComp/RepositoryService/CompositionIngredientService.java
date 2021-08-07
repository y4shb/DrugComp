package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.*;
import com.licious.DrugComp.Utils.CompositionUtils;
import com.licious.DrugComp.dto.CompositionResponse;
import com.licious.DrugComp.dto.IngredientInfo;
import com.licious.DrugComp.dto.request.CompositionDetailsRequest;
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
    @Autowired
    private IngredientRepository ingredientRepository;



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
        // Send to compositionResponseDTO : 'compositionIngredientList'
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
        // Send to compositionResponseDTO : 'ingredients'
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
        // Send to compositionResponseDTO : 'moleculeList'
        compositionResponse.setMoleculeList(moleculeList);


        return compositionResponse;
    }

    // OTHER CUSTOM SERVICE METHODS :

    public String addCompositionDetails(CompositionDetailsRequest inputRequest, String compositionName) {
        List<String> allIngredientNames = getIngredientList();
        List<String> allMoleculeNames = getMoleculeList();
        List<String> allCompositionNames = getCompositionList();
        // CHECK THE LINE BELOW AND VERIFY
        Boolean rxRequired = inputRequest.getRx_required().booleanValue();

        List<IngredientInfo> ingredientInfoList = inputRequest.getIngredients();
        for(IngredientInfo ingredient : ingredientInfoList) {
            String ingredientName = ingredient.getName();
            float strength = ingredient.getStrength();
            String unit = ingredient.getUnit();

            if(allIngredientNames.indexOf(ingredientName) == -1) {
                return "ingredient : " + ingredientName + " does not exist. Add Composition Failed :(";
            }
        }
        List<String> ingredientNames = ingredientInfoList.stream().map(i->i.getName()).collect(Collectors.toList());
        String moleculeName = CompositionUtils.getMoleculeName(ingredientNames);
        if(allMoleculeNames.indexOf(moleculeName) == -1) {
            Molecule moleculeX = new Molecule();
            moleculeX.setName(moleculeName);
            moleculeX.setRxRequired(rxRequired);
            moleculeRepository.save(moleculeX);
        }
        Molecule molecule = moleculeRepository.findOneByName(moleculeName).get();
        molecule.setRxRequired(rxRequired);
        moleculeRepository.save(molecule);

        int moleculeId = molecule.getId();
        if(allCompositionNames.indexOf(compositionName) > 0) {
            return "Composition ALready Exists!";
        }
        else {
            Composition compositionX = new Composition();
            compositionX.setName(compositionName);
            compositionRepository.save(compositionX);
        }
        Composition composition = compositionRepository.findOneByName(compositionName).get();
        int compositionId = composition.getId();

        for(IngredientInfo ingredientInfo : ingredientInfoList) {
            Ingredient ingredient = ingredientRepository.findOneByName(ingredientInfo.getName());

            composition = null;
            composition = compositionRepository.findById(compositionId).get();

            CompositionIngredient compositionIngredientX = new CompositionIngredient();
            compositionIngredientX.setComposition(composition);
            compositionIngredientX.setIngredient(ingredient);
            compositionIngredientX.setStrength(ingredientInfo.getStrength());
            compositionIngredientX.setUnit(ingredientInfo.getUnit());
            compositionIngredientRepository.save(compositionIngredientX);

            MoleculeIngredient moleculeIngredientX = new MoleculeIngredient();
            moleculeIngredientX.setMolecule(molecule);
            moleculeIngredientX.setIngredient(ingredient);
            moleculeIngredientRepository.save(moleculeIngredientX);
        }

        return "Add Composition Details Successful!";

    }


    public List<String> getIngredientList(){
        return ingredientRepository.findAll().stream().map(t->t.getName()).collect(Collectors.toList());
    }
    public List<String> getMoleculeList(){
        return moleculeRepository.findAll().stream().map(t->t.getName()).collect(Collectors.toList());
    }
    public List<String> getCompositionList(){
        return compositionRepository.findAll().stream().map(t->t.getName()).collect(Collectors.toList());
    }

}
