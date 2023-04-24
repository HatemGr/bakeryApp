package com.hexagonal.bakery.domain.service;

import com.hexagonal.bakery.domain.models.Cake;
import com.hexagonal.bakery.domain.models.CakeStatus;
import com.hexagonal.bakery.domain.models.Ingredient;
import com.hexagonal.bakery.domain.models.Order;
import com.hexagonal.bakery.domain.models.OrderLine;
import com.hexagonal.bakery.domain.models.OrderLineType;
import com.hexagonal.bakery.domain.repositories.BakeryRepository;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DomainBakeryService implements  BakeryService {

    private final BakeryRepository bakeryRepository;

    public DomainBakeryService(final BakeryRepository bakeryRepository) {
        this.bakeryRepository = bakeryRepository;
    }

    @Override
    public UUID createOrder(List<Cake> cakes) {
        final Order order = new Order(cakes);
        bakeryRepository.save(order);
        return order.getId();
    }

    @Override
    public UUID cancelOrder(UUID orderId) {
       Order order = getOrder(orderId);
       order.cancel();
       bakeryRepository.save(order);
       return order.getId();
    }

    @Override
    public void addCakeToOrder(UUID orderId, Cake cake) {
        Order order = getOrder(orderId);
        order.addCake(cake);
        bakeryRepository.save(order);
    }

    @Override
    public void removeCakeFromOrder(UUID orderId, Cake cake) {
        Order order = getOrder(orderId);
        order.removeCake(cake);
        bakeryRepository.save(order);
    }

    @Override
    public Cake mixIngredients(List<Ingredient> ingredients) {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        Cake cake =  new Cake( generatedString, ingredients);
        return cake;
    }

    @Override
    public void bakeCake(UUID orderId,Cake cake) {
        Order order = getOrder(orderId);
        OrderLine orderLine = order.getOrderLine(cake.getId());
        cake.bake();
        orderLine.makeLineBillable();
        bakeryRepository.save(order);
    }

    @Override
    public void tasteCake(Cake cake) {
        cake.taste();
    }

    @Override
    public void completeOrder(UUID orderId) {
        Order order = getOrder(orderId);
            order.complete();
            bakeryRepository.save(order);

    }

    @Override
    public Order getOrder(UUID orderId) {
        return bakeryRepository
                .findOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with given id doesn't exist"));
    }


}
