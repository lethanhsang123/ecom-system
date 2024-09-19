package com.sanzee.ecom.system.order.service.dataaccess.restaurant.mapper;

import com.sanzee.ecom.system.dataaccess.restaurant.entity.RestaurantEntity;
import com.sanzee.ecom.system.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.sanzee.ecom.system.domain.valueobject.Money;
import com.sanzee.ecom.system.domain.valueobject.ProductId;
import com.sanzee.ecom.system.domain.valueobject.RestaurantId;
import com.sanzee.ecom.system.order.service.domain.entity.Product;
import com.sanzee.ecom.system.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RestaurantDataAccessException("Restaurant could not be found!"));

        List<Product> restaurantProducts = restaurantEntities.stream().map(entity ->
                Product.builder().id(new ProductId(entity.getProductId()))
                        .name(entity.getProductName())
                        .price(new Money(entity.getProductPrice()))
                        .build())
                .toList();

        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurantEntity.getRestaurantActive())
                .build();
    }

}
