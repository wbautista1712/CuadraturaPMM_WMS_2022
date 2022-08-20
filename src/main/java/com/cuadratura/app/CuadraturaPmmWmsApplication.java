package com.cuadratura.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CuadraturaPmmWmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuadraturaPmmWmsApplication.class, args);
	}

}
