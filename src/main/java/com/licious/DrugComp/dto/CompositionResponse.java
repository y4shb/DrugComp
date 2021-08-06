package com.licious.DrugComp.dto;

import com.licious.DrugComp.models.CompositionIngredient;
import com.licious.DrugComp.models.Ingredient;
import com.licious.DrugComp.models.Molecule;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompositionResponse {
    List<CompositionIngredient> compositionIngredientList;
    List<Ingredient> ingredients;
    List<Molecule> moleculeList;

}
