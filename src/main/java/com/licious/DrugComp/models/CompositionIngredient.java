package com.licious.DrugComp.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "composition_ingredients")
@Data
@Entity
public class CompositionIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //add foreign key to Composition.id
    @JoinColumn(name = "composition_id",referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.MERGE)
    private Composition composition;
    //add foreign key to Ingredient.id
    @JoinColumn(name = "ingredient_id",referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.MERGE)
    private Ingredient ingredient;

    private String unit;
    private float strength;


}
