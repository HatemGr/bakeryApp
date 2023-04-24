package com.hexagonal.bakery.infrastructure.repository.mongo;

import com.hexagonal.bakery.domain.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataMongoBakeryRepository extends MongoRepository<Order, UUID> {
}
