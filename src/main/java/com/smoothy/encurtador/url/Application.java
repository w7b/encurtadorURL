package com.smoothy.encurtador.url;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class Application {
	static {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("DB_CONNECTION", dotenv.get("DB_CONNECTION"));
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
