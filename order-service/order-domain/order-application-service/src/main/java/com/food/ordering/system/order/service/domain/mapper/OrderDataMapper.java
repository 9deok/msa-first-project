package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.creaate.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.creaate.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.creaate.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder()
            .products(createOrderCommand.getItems().stream()
                .map(orderItem -> new Product(new ProductId(orderItem.getProducId())))
                .collect(Collectors.toList()))
            .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder()
            .customerId(new CustomerId(createOrderCommand.getCustomerId()))
            .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
            .deliverAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
            .price(new Money(createOrderCommand.getPrice()))
            .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
            .build();
    }


    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
            UUID.randomUUID(),
            address.getStreet(),
            address.getPostalCode(),
            address.getCity()
        );
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
        List<com.food.ordering.system.order.service.domain.dto.creaate.OrderItem> items) {
        return items.stream()
            .map(orderItem -> OrderItem.Builder.builder()
                .product(new Product(new ProductId(orderItem.getProducId())))
                .price(new Money(orderItem.getPrice()))
                .quantity(orderItem.getQuantity())
                .subTotal(new Money(orderItem.getSubTotal()))
                .build()).collect(Collectors.toList());
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
            .orderTrackingId(order.getTrackingId().getValue())
            .orderStatus(order.getOrderStatus())
            .build();
    }
}
