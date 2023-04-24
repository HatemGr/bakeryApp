package com.hexagonal.bakery.domain.models;

import com.hexagonal.bakery.domain.exception.DomainException;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Cake {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;
    private String name;
    private List<Ingredient> ingredients;
    private Double price;
    private CakeStatus status;

    public Cake(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.status = CakeStatus.ORDERED;
        this.price = ingredients.stream().map(Ingredient::getPrice).reduce((double) 0, Double::sum);
    }

    public void taste(){
        System.out.println("It's good");
    }

    public void bake(){
        validateState();
        this.status = CakeStatus.IN_PREPARATION;
    }

    private void validateState() {
        if (CakeStatus.READY.equals(status)) {
            throw new DomainException("The cake is already baked, don't you dare burn it !");
        }

        if (CakeStatus.IN_PREPARATION.equals(status)) {
            throw new DomainException("The cake is already in the over !");
        }
    }


}

