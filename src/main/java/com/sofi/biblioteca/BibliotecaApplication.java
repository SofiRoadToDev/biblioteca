package com.sofi.biblioteca;

import com.sofi.biblioteca.entities.Autor;
import com.sofi.biblioteca.entities.Editorial;
import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.repositories.AutorRepository;
import com.sofi.biblioteca.repositories.EditorialRepository;
import com.sofi.biblioteca.repositories.LibroRepository;
import com.sofi.biblioteca.services.AutorService;
import com.sofi.biblioteca.services.EditorialService;
import com.sofi.biblioteca.services.LibroService;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;
import java.util.logging.Logger;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {

		SpringApplication.run(BibliotecaApplication.class, args);


	}

	@Bean
	CommandLineRunner testRepos(LibroRepository libroService, AutorRepository autorService, EditorialRepository editorialService){
		return args -> {
			Autor autor = Autor.builder()
					.apellido("Lopez")
					.nombre("Pepe")
					.build();

			Editorial editorial = Editorial.builder()
					.nombre("EPOXI")
					.build();


			Editorial editorialS = editorialService.save(editorial);
			Autor autorS = autorService.save(autor);


			Libro libro = Libro.builder()
					.tema("Lengua")
					.isbn("6554767ghhg")
					.titulo("Las vocales")
					.editorial(editorialS)
					.autores(Set.of(autorS))
					.build();


			libroService.save(libro);
		};


	}

}
