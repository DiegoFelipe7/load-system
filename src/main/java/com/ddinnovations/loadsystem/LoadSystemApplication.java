package com.ddinnovations.loadsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class LoadSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadSystemApplication.class, args);
	}

}
