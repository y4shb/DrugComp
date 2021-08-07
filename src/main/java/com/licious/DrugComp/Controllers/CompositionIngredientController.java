package com.licious.DrugComp.Controllers;

import com.licious.DrugComp.RepositoryService.CompositionIngredientService;
import com.licious.DrugComp.RepositoryService.CompositionService;
import com.licious.DrugComp.Utils.CompositionUtils;
import com.licious.DrugComp.dto.CompositionResponse;
import com.licious.DrugComp.dto.request.CompositionDetailsRequest;
import com.licious.DrugComp.dto.response.CompositionDetailsStatus;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CompositionIngredientController {
    @Autowired
    CompositionIngredientService compositionIngredientService;


    // FIRST API
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

    // BULK INSERTION
    @PostMapping(value = "composition/bulk", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CompositionDetailsStatus> addCompositionDetails(@RequestBody List<CompositionDetailsRequest> inputRequest) {
        int flag = 0;
        List<CompositionDetailsStatus> responseList = new ArrayList<>();

        for(CompositionDetailsRequest compositionDetailsRequest : inputRequest) {
            CompositionDetailsStatus compositionDetailsStatus = new CompositionDetailsStatus();
            String compositionName = CompositionUtils.getCompositionName(compositionDetailsRequest.getIngredients());
            String alert = compositionIngredientService.addCompositionDetails(compositionDetailsRequest, compositionName);

            if(alert == "Add Composition Details Successful!")
                flag = 1;
            compositionDetailsStatus.setCompositionName(compositionName);
            compositionDetailsStatus.setInsertStatus(alert);
            responseList.add(compositionDetailsStatus);
        }
        return responseList;
    }

}
