package com.food.ordering.system.order.service.domain.valueobject;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class StreetAddress {
    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;
}
