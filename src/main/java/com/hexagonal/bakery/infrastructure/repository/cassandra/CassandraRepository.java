package com.hexagonal.bakery.infrastructure.repository.cassandra;

import com.hexagonal.bakery.domain.models.Cake;
import com.hexagonal.bakery.domain.models.Order;
import com.hexagonal.bakery.domain.repositories.BakeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CassandraRepository implements BakeryRepository {

    private final SpringDataCassandraBakeryRepository bakeryRepository;
    private final EntityMapper entityMapper;


    @Autowired
    public CassandraRepository(EntityMapper entityMapper, SpringDataCassandraBakeryRepository bakeryRepository) {
        this.bakeryRepository = bakeryRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Optional<Order> findOrderById(UUID id) {
        OrderEntity orderEntity= bakeryRepository.findById(id).get();
        return Optional.ofNullable(entityMapper.toOrder(orderEntity));
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = entityMapper.toOrderEntity(order);
        bakeryRepository.save(orderEntity);
    }

}
