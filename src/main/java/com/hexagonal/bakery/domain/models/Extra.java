package com.hexagonal.bakery.domain.models;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Extra {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;
    private String jobDescription;
    private Double price;
}
