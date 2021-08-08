package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_STATUS")
@Getter
@Setter
@NoArgsConstructor
public class OrderStatus {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY)
    private List <BurgerOrder> burgerOrders = new ArrayList<>();

    public OrderStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }
}
