package com.hexagonal.bakery.infrastructure.config;

import com.hexagonal.bakery.infrastructure.repository.cassandra.SpringDataCassandraBakeryRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories(basePackageClasses = SpringDataCassandraBakeryRepository.class)
public class CassandraConfiguration {

}
