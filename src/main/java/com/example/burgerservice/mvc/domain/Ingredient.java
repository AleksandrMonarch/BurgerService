package com.example.burgerservice.mvc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "INGREDIENT")
@NoArgsConstructor(force = true)
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

    public Ingredient(String id, String name, IngredientType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

//    public static enum Type {
//        CHEESE,
//        SOUSE,
//        WRAP,
//        MEAT,
//        LETTUCE
//    }
}
