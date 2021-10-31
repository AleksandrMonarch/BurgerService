package com.example.burgerservice.mvc.domain;

import com.example.burgerservice.security.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "ORDER_NAME")
    private String orderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_STATUS_ID", referencedColumnName = "ID")
    private OrderStatus orderStatus;

    @Column(name = "CREATE_AT")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private Address address;

//    @CreditCardNumber(message = "not valid credit card number")
//    @Column(name = "CC_NUMBER")
//    private String ccNumber;
//
//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message = "not valid expiration date")
//    @Column(name = "CC_EXPIRATION")
//    private String ccExpiration;
//
//    @Column(name = "CC_CVV")
//    @Digits(integer = 3, message = "not valid ccCVV", fraction = 0)
//    private String ccCVV;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true, mappedBy = "burgerOrder", fetch = FetchType.EAGER)
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

    public void addAddress(Address address) {
        if (Objects.isNull(address)) {
            log.error("null address found");
            return;
        }
        this.address = address;
        address.getBurgerOrders().add(this);
    }

    public void removeAddress(Address address) {
        if (Objects.isNull(address)) {
            log.error("null address found");
            return;
        }
        this.address = null;
        address.getBurgerOrders().remove(this);
    }

    public void addOrderStatus(OrderStatus orderStatus) {
        if (Objects.isNull(orderStatus)) {
            log.error("null order status found");
            return;
        }
        this.orderStatus = orderStatus;
        orderStatus.getBurgerOrders().add(this);
    }

    public void removeOrderStatus(OrderStatus orderStatus) {
        if (Objects.isNull(orderStatus)) {
            log.error("null order status found");
            return;
        }
        this.orderStatus = null;
        orderStatus.getBurgerOrders().remove(this);
    }

    //it can be incorrect

    public void changeStatus(OrderStatus newOrderStatus) {
        if (Objects.isNull(this.orderStatus)) {
            addOrderStatus(newOrderStatus);
            return;
        }
        removeOrderStatus(this.orderStatus);
        addOrderStatus(newOrderStatus);
    }

    @Override
    public String toString() {
        return "BurgerOrder{" +
                "id='" + id + '\'' +
                ", orderName='" + orderName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @PrePersist
    public void initializeCreatedDateField() {
        this.createdAt = LocalDateTime.now();
    }
}
