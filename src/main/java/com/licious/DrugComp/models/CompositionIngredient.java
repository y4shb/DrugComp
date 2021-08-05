package com.licious.DrugComp.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "composition_ingredients")
@Data
public class CompositionIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //add foreign key to Composition.id
    @JoinColumn(name = "composition_id")
    private Composition composition;
    //add foreign key to Ingredient.id
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private String unit;
    private Float strength;
}
