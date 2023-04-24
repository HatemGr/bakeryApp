package com.hexagonal.bakery.infrastructure.repository.cassandra;

import com.hexagonal.bakery.domain.models.OrderLine;
import com.hexagonal.bakery.domain.models.OrderStatus;

import java.util.List;
import java.util.UUID;

public class OrderEntity {

    private UUID id;
    private OrderStatus status;
    private List<OrderLineEntity> lineEntities;
    private Double price;
}
