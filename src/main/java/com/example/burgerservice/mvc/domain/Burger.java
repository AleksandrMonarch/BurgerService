package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BURGER")
@Getter
@Setter
@Slf4j
public class Burger {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @NotBlank
    @Size(min = 5, max = 50, message = "name must be at least 5 characters long")
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "BURGER_INGREDIENTS",
            joinColumns = @JoinColumn(name = "BURGER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENT_ID", referencedColumnName = "ID"))
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private BurgerOrder burgerOrder;

    public void addIngredient(Ingredient ingredient) {
        if (Objects.isNull(ingredient)) {
            log.error("null ingredient found");
            return;
        }
        ingredients.add(ingredient);
        ingredient.getBurgers().add(this);
    }

    public void removeIngredient(Ingredient ingredient) {
        if (Objects.isNull(ingredient)) {
            log.error("null ingredient found");
            return;
        }
        ingredients.remove(ingredient);
        ingredient.getBurgers().remove(this);
    }
}
