package com.licious.DrugComp.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "molecule_ingredients")
@Data
@Entity
public class MoleculeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Foreign key to Molecule.molID;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "molecule_id", referencedColumnName = "id")
    private Molecule molecule;
    //Add Foreign key to Ingredient.ingdID;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredient ingredient;
}
