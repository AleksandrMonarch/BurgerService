package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private final String id;

    @Column(name = "NAME")
    private final String name;

//    @Column(name = "TYPE")
//    private final Type type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
