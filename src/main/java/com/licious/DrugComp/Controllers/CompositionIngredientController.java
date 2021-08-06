package com.licious.DrugComp.Controllers;

import com.licious.DrugComp.RepositoryService.CompositionIngredientService;
import com.licious.DrugComp.RepositoryService.CompositionService;
import com.licious.DrugComp.dto.CompositionResponse;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompositionIngredientController {
    @Autowired
    CompositionIngredientService compositionIngredientService;

    //First API
    @GetMapping("/composition/id")
    public CompositionResponse getCompositionDetailsbyCompId(@RequestParam("composition_id") int compId) {
        return compositionIngredientService.getCompositionDetailsById(compId);
    }
    @GetMapping("/composition/name")
    public CompositionResponse getCompositionDetailsbyCompName(@RequestParam("composition_id") String compName) {
        return compositionIngredientService.getCompositionDetailsByName(compName);
    }
    @GetMapping("/composition/ISU")
    public List<CompositionIngredient> getCompositionsByISU(@RequestParam("ingredientName") String ingredientName,
                                                    @RequestParam("strength") float strength,
                                                    @RequestParam("unit") String unit) {
        return compositionIngredientService.getCompositionIngredientByISU(ingredientName, strength, unit);
    }

}
