package com.example.burgerservice.mvc.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "BURGER")
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
    private List<Ingredient> ingredients;
}
