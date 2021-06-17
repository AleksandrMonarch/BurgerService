package com.example.burgerservice.mvc.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Slf4j
public class BurgerOrder {

//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BurgerOrder.class);

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;

//    @CreditCardNumber(message = "not valid credit card number")
    private String ccNumber;

//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message = "not valid expiration date")
    private String ccExpiration;

    @Digits(integer = 3, message = "not valid ccCVV", fraction = 0)
    private String ccCVV;
    private List<Burger> burgers = new ArrayList<>();

    public void addBurgerInList(Burger burger) {
//        if (burger != null) burgers.add(burger);
        if (Objects.nonNull(burger)) {
            burgers.add(burger);
        } else {
            log.error("get burger null value");
            throw new IllegalArgumentException();
        }
    }
}
