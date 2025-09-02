package br.edu.ifto.aula02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Aula02Application {

	public static void main(String[] args) {
		SpringApplication.run(Aula02Application.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));

	}

}
