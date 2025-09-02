package aulasPW2.Aula1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Horário de atendimento da disciplina as quintas-feiras à tarde!

@SpringBootApplication
public class Aula1Application {

	public static void main(String[] args) {
		SpringApplication.run(Aula1Application.class, args);
	}
}
