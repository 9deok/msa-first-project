package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
            .map(product -> product.getId().getValue())
            .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity = restaurantEntities.stream().findFirst()
            .orElseThrow(() -> new RestaurantDataAccessException("Restaurant could not be found!"));

        List<Product> restaurantProducts = restaurantEntities.stream().map(restaurant
            -> new Product(new ProductId(restaurant.getProductId()), restaurant.getProductName(),
            new Money(restaurant.getProductPrice()))).toList();

        return Restaurant.Builder.builder()
            .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
            .products(restaurantProducts)
            .active(restaurantEntity.getRestaurantActive())
            .build();
    }
}
