package com.hexagonal.bakery.domain.models;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class OrderLine {
    private final UUID id;
    private final String label;
    private final Double price;
    private final List<Ingredient> composition;
    private final OrderLineType type;
    private OrderLineStatus status;

    public OrderLine(Cake cake) {
        this.id = cake.getId();
        this.label = cake.getName();
        this.price = cake.getPrice();
        this.composition = cake.getIngredients();
        this.type = OrderLineType.CAKE;
        this.status = OrderLineStatus.PENDING;
    }

    public  OrderLine(Extra extra) {
        this.id = extra.getId();
        this.label = extra.getJobDescription();
        this.price = extra.getPrice();
        this.type = OrderLineType.EXTRA;
        this.composition = List.of();
        this.status = OrderLineStatus.BILLABLE;
    }

    public void makeLineBillable() {
        this.status = OrderLineStatus.BILLABLE;
    }
}
