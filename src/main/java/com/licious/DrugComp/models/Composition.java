package com.licious.DrugComp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "compositions")
@Data
@Entity
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "composition")
    @JsonIgnore(value = true)
    private List<CompositionIngredient> compositionIngredientList;


}
