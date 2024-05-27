package com.nicolasnunez.LiterAlura;

import com.nicolasnunez.LiterAlura.main.Main;
import com.nicolasnunez.LiterAlura.service.ConnectionAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.convertJson();
	}
}
