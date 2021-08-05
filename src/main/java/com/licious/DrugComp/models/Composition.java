package com.licious.DrugComp.models;


import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "compositions")
@Data
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;


}
