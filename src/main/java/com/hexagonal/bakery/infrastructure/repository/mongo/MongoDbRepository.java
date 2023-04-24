package com.hexagonal.bakery.infrastructure.repository.mongo;

import com.hexagonal.bakery.domain.models.Order;
import com.hexagonal.bakery.domain.repositories.BakeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Primary
public class MongoDbRepository implements BakeryRepository {

    private final SpringDataMongoBakeryRepository bakeryRepository;

    @Autowired
    public MongoDbRepository(SpringDataMongoBakeryRepository bakeryRepository) {
        this.bakeryRepository = bakeryRepository;
    }

    @Override
    public Optional<Order> findOrderById(UUID id) {
        return bakeryRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        bakeryRepository.save(order);
    }
}
