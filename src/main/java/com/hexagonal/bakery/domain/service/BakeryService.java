package com.hexagonal.bakery.domain.service;

import com.hexagonal.bakery.domain.models.Cake;
import com.hexagonal.bakery.domain.models.Ingredient;
import com.hexagonal.bakery.domain.models.Order;

import java.util.List;
import java.util.UUID;

public interface BakeryService {
    UUID createOrder(List<Cake> cakes);
    UUID cancelOrder(UUID orderId);
    void addCakeToOrder(UUID orderId, Cake cake);
    void removeCakeFromOrder(UUID orderId, Cake cake);
    Cake mixIngredients(List<Ingredient> ingredients);
    void bakeCake(UUID orderId,Cake cake);
    void tasteCake(Cake cake);
    void completeOrder(UUID orderId);
    Order getOrder(UUID orderId);

}
