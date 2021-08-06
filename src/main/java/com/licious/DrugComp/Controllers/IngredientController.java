package com.licious.DrugComp.Controllers;

import com.licious.DrugComp.RepositoryService.CompositionIngredientService;
import com.licious.DrugComp.RepositoryService.IngredientService;
import com.licious.DrugComp.dto.CompositionResponse;
import com.licious.DrugComp.models.Composition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    // Second API
    @GetMapping("/ingredient/ISU/id")
    public List<Composition> getCompositionDetailsbyISUId(@RequestParam("ingredient_id") int ingredientId,
                                                          @RequestParam("strength") Float strength,
                                                          @RequestParam("unit") String unit) {
        return ingredientService.getCompositionsByIngredientIdStrengthUnit(ingredientId, strength, unit);
    }
    @GetMapping("/ingredient/ISU/name")
    public List<Composition> getCompositionDetailsbyISUName(@RequestParam("name") String ingredientName,
                                                              @RequestParam("strength") Float strength,
                                                              @RequestParam("unit") String unit) {
        return ingredientService.getCompositionsByIngredientNameStrengthUnit(ingredientName, strength, unit);
    }

    // Third API
    @GetMapping("/ingredient/ISURx/id")
    public List<Composition> getCompositionDetailsbyISURxId(@RequestParam("ingredient_id") int ingredientId,
                                                          @RequestParam("strength") Float strength,
                                                          @RequestParam("unit") String unit,
                                                            @RequestParam("rxRequired") Boolean rxRequired) {
        return ingredientService.getCompositionsByIngredientIdStrengthUnitRx(ingredientId, strength, unit, rxRequired);
    }
    @GetMapping("/ingredient/ISURx/name")
    public List<Composition> getCompositionDetailsbyISURxName(@RequestParam("name") String ingredientName,
                                                            @RequestParam("strength") Float strength,
                                                            @RequestParam("unit") String unit,
                                                            @RequestParam("rxRequired") Boolean rxRequired) {
        return ingredientService.getCompositionsByIngredientNameStrengthUnitRx(ingredientName, strength, unit, rxRequired);
    }
}
