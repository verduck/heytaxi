package com.moca.heytaxi;

import com.moca.heytaxi.properties.TwilioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan("com.moca.heytaxi.properties")
public class HeyTaxiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeyTaxiApplication.class, args);
	}

}
