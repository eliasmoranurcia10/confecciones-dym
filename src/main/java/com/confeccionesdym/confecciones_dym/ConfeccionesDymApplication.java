package com.confeccionesdym.confecciones_dym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ConfeccionesDymApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfeccionesDymApplication.class, args);
	}

}
