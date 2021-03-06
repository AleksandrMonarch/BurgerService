package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENT_TYPE")
@NoArgsConstructor
@Getter
@Setter
public class IngredientType {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    public IngredientType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
