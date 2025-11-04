package com.omniserve.shipmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = {
				"com.omniserve.shipmentservice",
				"com.omniserve.commondblib"
		}
)
@EntityScan(
		"com.omniserve.commondblib.entity"
)
@EnableJpaRepositories(
		"com.omniserve.commondblib.repository"
)
public class ShipmentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentserviceApplication.class, args);
	}

}
