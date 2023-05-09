package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.order.service.domain.entity.Order;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class OrderEvent implements DomainEvent<Order> {

    private final Order order;
    private final ZonedDateTime createdAt;
}
