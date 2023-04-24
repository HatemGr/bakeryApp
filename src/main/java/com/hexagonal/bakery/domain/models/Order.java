package com.hexagonal.bakery.domain.models;

import com.hexagonal.bakery.domain.exception.DomainException;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Getter
public class Order {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;
    private OrderStatus status;
    private List<OrderLine> lines;
    private Double priceBeforeMargin;
    private Double marginTax = 1.2;
    
    public Order(final Cake cake) {
        this.lines = List.of(new OrderLine(cake));
        this.status = OrderStatus.CREATED;
        this.priceBeforeMargin = cake.getPrice();
    }

    public Order(final List<Cake> cakes) {
        cakes.forEach(this::addCake);
        this.status = OrderStatus.CREATED;
    }

    public void addCake(Cake cake) {
        validateState();
        validateCake(cake);
        lines.add(new OrderLine(cake));
        priceBeforeMargin = cake.getPrice();
    }

    public void removeCake(Cake cake) {
        validateState();
        validateCake(cake);
        lines.remove(getOrderLine(cake.getId()));
        priceBeforeMargin = priceBeforeMargin - cake.getPrice();
    }

    public void complete(){
        validateState();
        this.status = OrderStatus.COMPLETED;
    }

    public void cancel(){
        validateState();
        this.status = OrderStatus.CANCELED;
    }

    public OrderLine getOrderLine(UUID id) {
        return lines.stream()
                .filter(orderLine -> orderLine.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DomainException("OrderLine with " + id + " doesn't exist."));

    }

    public Double billingPrice() {
        return this.priceBeforeMargin * marginTax;
    }

    private void validateState() {
        if (OrderStatus.COMPLETED.equals(status)) {
            throw new DomainException("The order is in completed state, no change allow.");
        }
    }

    private void validateCake(final Cake cake) {
        if (cake == null) {
            throw new DomainException("A cake can't be null, except at Starbucks.");
        }
    }

}
