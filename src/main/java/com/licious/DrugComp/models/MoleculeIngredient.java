package com.licious.DrugComp.models;

import javax.persistence.*;

public class MoleculeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Foreign key to Molecule.molID;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "molecule_id", referencedColumnName = "molecule.id")
    private int moleculeId;
    //Add Foreign key to Ingredient.ingdID;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient.id")
    private int ingredientId;
}
