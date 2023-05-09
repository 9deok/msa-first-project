package com.food.ordering.system.order.service.domain.ports.input.service;

import com.food.ordering.system.order.service.domain.dto.creaate.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.creaate.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackingOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackingOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
