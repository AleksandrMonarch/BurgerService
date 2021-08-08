package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ADDRESS")
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP_NUMBER")
    private String zipNumber;

    @OneToMany(orphanRemoval = true, mappedBy = "address", fetch = FetchType.LAZY)
    private List<BurgerOrder> burgerOrders = new ArrayList<>();
}
