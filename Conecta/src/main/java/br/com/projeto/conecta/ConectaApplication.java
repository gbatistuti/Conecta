package br.com.projeto.conecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@EntityScan(basePackages = "br.com.projeto.domain")
@ComponentScan(basePackages = {"br.*"})
public class ConectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConectaApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}