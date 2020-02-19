package org.coviam.flightsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FlightsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsearchApplication.class, args);
	}

}
