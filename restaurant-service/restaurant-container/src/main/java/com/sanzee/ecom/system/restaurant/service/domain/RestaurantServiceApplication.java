package com.sanzee.ecom.system.restaurant.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.sanzee.ecom.system.restaurant.service.dataaccess")
@EntityScan(basePackages = {"com.sanzee.ecom.system.restaurant.service.dataaccess", "com.sanzee.ecom.system.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.sanzee.ecom.system")
public class RestaurantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }

}
