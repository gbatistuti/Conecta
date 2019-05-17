package br.com.projeto.conecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ConectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConectaApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}