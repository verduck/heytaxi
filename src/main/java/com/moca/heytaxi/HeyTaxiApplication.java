package com.moca.heytaxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.moca.heytaxi.properties")
public class HeyTaxiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeyTaxiApplication.class, args);
	}

}
