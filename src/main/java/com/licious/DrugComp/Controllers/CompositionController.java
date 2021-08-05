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

    @GetMapping("/composition")
    public CompositionResponse getCompositionDetailsbyCompId(@RequestParam int compId) {
        return compositionIngredientService.getCompositionDetailsById(compId);
    }
}
