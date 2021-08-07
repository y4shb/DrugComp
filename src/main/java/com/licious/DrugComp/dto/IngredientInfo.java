package com.licious.DrugComp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IngredientInfo {
    String name;
    float strength;
    String unit;
}
