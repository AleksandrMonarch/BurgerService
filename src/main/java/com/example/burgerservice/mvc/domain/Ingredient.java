package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INGREDIENT")
@NoArgsConstructor(force = true)
@Getter
@Setter
public class Ingredient {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    private IngredientType type;

    @ManyToMany(mappedBy = "ingredients")
    private List<Burger> burgers = new ArrayList<>();

    public Ingredient(String id, String name, IngredientType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
