package com.hexagonal.bakery.infrastructure.repository.cassandra;

import com.hexagonal.bakery.domain.models.Ingredient;

import java.util.List;
import java.util.UUID;

public class OrderLineEntity {
    private UUID id;
    private String reference;
    private Double price;
    private OrderLineEntityCategory category;
    private List<Ingredient> composition;


    public OrderLineEntity() {
    }

}
