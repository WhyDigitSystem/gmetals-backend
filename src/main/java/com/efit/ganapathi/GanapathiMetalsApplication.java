package com.efit.ganapathi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class GanapathiMetalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GanapathiMetalsApplication.class, args);
	}

}
