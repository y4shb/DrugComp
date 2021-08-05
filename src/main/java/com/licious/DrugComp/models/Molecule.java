package com.licious.DrugComp.models;

import lombok.Data;

import javax.persistence.*;

@Table(name = "molecules")
@Data
public class Molecule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private  Boolean rxRequired;
}
