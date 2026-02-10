package com.aluracursos.desafio_literalura;

import com.aluracursos.desafio_literalura.model.RespuestaAPI;
import com.aluracursos.desafio_literalura.principal.Principal;
import com.aluracursos.desafio_literalura.service.ConsumoAPI;
import com.aluracursos.desafio_literalura.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(DesafioLiteraluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
	}
}
