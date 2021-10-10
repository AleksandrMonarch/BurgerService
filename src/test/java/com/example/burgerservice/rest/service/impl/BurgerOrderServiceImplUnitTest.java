package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.OrderRepository;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;

import com.example.burgerservice.rest.dto.BurgerOrderDto;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@WebMvcTest
public class BurgerOrderServiceImplUnitTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderStatusRepository orderStatusRepository;

    private MockMvc mockMvc;

    @Autowired
    public BurgerOrderServiceImplUnitTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

//    void cancelOrderTest() {
//        // TODO: 26.09.2021 try it
//        //Jackson JSON ObjectMapper class
//
//        OrderStatus inProgressOrderStatus = new OrderStatus();
//        inProgressOrderStatus.setId("IP");
//        inProgressOrderStatus.setStatus("In progress");
//
//        OrderStatus canceledOrderStatus = new OrderStatus();
//        canceledOrderStatus.setId("CL");
//        canceledOrderStatus.setStatus("Canceled");
//
//        BurgerOrder testBurgerOrderDao = new BurgerOrder();
//        testBurgerOrderDao.setId("1");
//        testBurgerOrderDao.setOrderStatus(inProgressOrderStatus);
//        inProgressOrderStatus.setBurgerOrders(Arrays.asList(testBurgerOrderDao));
//
//        Mockito.when(orderRepository.findById("1").orElseThrow(EntityNotFoundException::new))
//                .thenReturn(testBurgerOrderDao);
//
//        Mockito.when(orderStatusRepository.getOrderStatusById("CL")).thenReturn(canceledOrderStatus);
//
//        BurgerOrderDto resultBurgerOrderDto = new BurgerOrderDto();
//        resultBurgerOrderDto.setId("1");
//        resultBurgerOrderDto.setOrderStatus("CL");
//
//        Assertions.assertEquals(resultBurgerOrderDto, );
//    }
}
