package com.hexagonal.bakery;

import com.hexagonal.bakery.domain.models.Cake;
import com.hexagonal.bakery.domain.models.Ingredient;
import com.hexagonal.bakery.domain.models.Order;
import com.hexagonal.bakery.domain.service.DomainBakeryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;
import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BakeryApplication {
	private static DomainBakeryService domainBakeryService;
	private static Order order;
	private static Cake cake;
	private static Ingredient ingredient;

	public static void main(String[] args) {

		SpringApplication.run(BakeryApplication.class, args);
		Ingredient cheese = new Ingredient("Cheese", 50.4);
		Ingredient sugar = new Ingredient("Sugar", 12.5);

		Cake myCake = new Cake("Cheesecake", List.of(cheese,sugar));
		System.out.println("This cake's price is : " + myCake.getPrice());

		UUID test = domainBakeryService.createOrder(List.of(myCake));
		System.out.println(test);
	}

}
