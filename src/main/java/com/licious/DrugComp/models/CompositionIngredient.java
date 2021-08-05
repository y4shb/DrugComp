package com.licious.DrugComp.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "composition_ingredients")
@Data
public class CompositionIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //add foreign key to Composition.id
    @JoinColumn(name = "composition_id", referencedColumnName = "composition.id")
    private int compositionId;
    //add foreign key to Ingredient.id
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient.id")
    private int ingredientId;

    private String unit;
    private Float strength;
}
