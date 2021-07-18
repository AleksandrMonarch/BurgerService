package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Entity
@Table(name = "BURGER_ORDER")
@Getter
@Setter
public class BurgerOrder {

//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BurgerOrder.class);
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Column(name = "DELIVERY_NAME")
    private String deliveryName;

    @Column(name = "DELIVERY_STREET")
    private String deliveryStreet;

    @Column(name = "DELIVERY_CITY")
    private String deliveryCity;

    @Column(name = "DELIVERY_STATE")
    private String deliveryState;

    @Column(name = "DELIVERY_ZIP")
    private String deliveryZip;

//    @CreditCardNumber(message = "not valid credit card number")
    @Column(name = "CC_NUMBER")
    private String ccNumber;

//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message = "not valid expiration date")
    @Column(name = "CC_EXPIRATION")
    private String ccExpiration;

    @Column(name = "CC_CVV")
    @Digits(integer = 3, message = "not valid ccCVV", fraction = 0)
    private String ccCVV;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "burgerOrder")
    private List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger burger) {
        if (Objects.isNull(burger)) {
            log.error("null burger found");
            return;
        }
        burgers.add(burger);
        burger.setBurgerOrder(this);
    }

    public void removeBurger(Burger burger) {
        if (Objects.isNull(burger)) {
            log.error("null burger found");
            return;
        }
        burgers.remove(burger);
        burger.setBurgerOrder(null);
    }
}
