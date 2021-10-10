package com.example.burgerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
//@EnableConfigurationProperties
@EnableFeignClients
public class BurgerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerServiceApplication.class, args);
    }
}