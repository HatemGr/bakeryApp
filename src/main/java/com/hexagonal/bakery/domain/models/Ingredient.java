package com.hexagonal.bakery.domain.models;

import jakarta.persistence.GeneratedValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Ingredient {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;
    private String name;
    private Double price;

    public Ingredient(String name, Double price){
        this.name = name;
        this.price = price;
    }
}
