package com.breweries.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		//BreweryService.listBreweries();
		//BreweryService.searchBreweriesByName("Brewing Co");
	}

}
