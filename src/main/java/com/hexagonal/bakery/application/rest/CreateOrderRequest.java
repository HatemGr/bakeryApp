package com.hexagonal.bakery.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hexagonal.bakery.domain.models.Cake;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateOrderRequest {
    @NotNull
    private List<Cake> cakes;

    @JsonCreator
    public CreateOrderRequest(@JsonProperty("cakes") @NotNull final List<Cake> cakes) {
        this.cakes = cakes;
    }

    public List<Cake> getCakes() {
        return cakes;
    }
}
