package com.moca.heytaxi;

import com.moca.heytaxi.config.TwilioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TwilioProperties.class)
public class HeyTaxiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeyTaxiApplication.class, args);
	}

}
