package com.licious.DrugComp.Controllers;

import com.licious.DrugComp.RepositoryService.CompositionIngredientService;
import com.licious.DrugComp.RepositoryService.IngredientService;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {
    @Autowired
    IngredientService ingredientService;


    // SECOND API : Give all compositions where ingredient is X with strength S and unit U

    @GetMapping("/ingredient/ISU/id")
    public List<Composition> getCompositionDetailsbyISUId(@RequestParam("ingredient_id") int ingredientId,
                                                          @RequestParam("strength") float strength,
                                                          @RequestParam("unit") String unit) {
        return ingredientService.getCompositionsByIngredientIdStrengthUnit(ingredientId, strength, unit);
    }
    @GetMapping("/ingredient/ISU/name")
    public List<CompositionIngredient> getCompositionDetailsbyISUName(@RequestParam("name") String ingredientName,
                                                                      @RequestParam("strength") float strength,
                                                                      @RequestParam("unit") String unit) {
        return ingredientService.getCompositionsByIngredientNameStrengthUnit(ingredientName, strength, unit);
    }

    // THIRD API : Give all compositions where ingredient is X with strength S and unit U and rx_required is Y

    @GetMapping("/ingredient/ISURx/name")
    public List<CompositionIngredient> getCompositionDetailsByISURxId(@RequestParam("name") String ingredientName,
                                                            @RequestParam("strength") float strength,
                                                            @RequestParam("unit") String unit,
                                                            @RequestParam("rx_required") Boolean rxRequired) {
        List<CompositionIngredient> compositionList = ingredientService.getCompositionsByIngredientNameStrengthUnitRx(ingredientName, strength, unit, rxRequired);
        return compositionList;
    }



}
