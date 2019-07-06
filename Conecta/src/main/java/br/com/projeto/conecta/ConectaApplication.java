package br.com.projeto.conecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ConectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConectaApplication.class, args);
	}
}