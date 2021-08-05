package com.licious.DrugComp.Controllers;

import com.licious.DrugComp.RepositoryService.CompositionIngredientService;
import com.licious.DrugComp.RepositoryService.CompositionService;
import com.licious.DrugComp.dto.CompositionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompositionController {
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
}
