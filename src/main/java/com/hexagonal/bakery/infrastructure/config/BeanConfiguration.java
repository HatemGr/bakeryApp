package com.hexagonal.bakery.infrastructure.config;

import com.hexagonal.bakery.BakeryApplication;
import com.hexagonal.bakery.domain.repositories.BakeryRepository;
import com.hexagonal.bakery.domain.service.BakeryService;
import com.hexagonal.bakery.domain.service.DomainBakeryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BakeryApplication.class)
public class BeanConfiguration {

    @Bean
    BakeryService bakeryService(final BakeryRepository bakeryRepository) {
        return new DomainBakeryService(bakeryRepository);
    }
}
