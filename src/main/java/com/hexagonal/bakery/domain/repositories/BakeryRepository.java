package com.hexagonal.bakery.domain.repositories;

import com.hexagonal.bakery.domain.models.Cake;
import com.hexagonal.bakery.domain.models.Ingredient;
import com.hexagonal.bakery.domain.models.Order;

import java.util.Optional;
import java.util.UUID;

public interface BakeryRepository {
    Optional<Order> findOrderById(UUID id);
    void save(Order order);


}
