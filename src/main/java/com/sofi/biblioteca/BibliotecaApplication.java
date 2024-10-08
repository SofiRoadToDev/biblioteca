package com.sofi.biblioteca;

import com.sofi.biblioteca.controllers.LibroController;
import com.sofi.biblioteca.entities.Autor;
import com.sofi.biblioteca.entities.Editorial;
import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.repositories.AutorRepository;
import com.sofi.biblioteca.repositories.EditorialRepository;
import com.sofi.biblioteca.repositories.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@SpringBootApplication
@Configuration
public class BibliotecaApplication {


	public static void main(String[] args) {

		SpringApplication.run(BibliotecaApplication.class, args);


	}

	@Bean
	CommandLineRunner testRepos(LibroRepository libroService,
								AutorRepository autorService,
								EditorialRepository editorialService){
		return args -> {

			Autor autor = Autor.builder()
					.apellido("Lopez")
					.nombre("Pepe")
					.build();



			Editorial editorial = Editorial.builder()
					.nombre("EPOXI")
					.build();

			Editorial editorial2 = Editorial.builder()
					.nombre("Good Vibes")
					.build();


			Libro libro = Libro.builder()
					.tema("Lengua")
					.isbn("jhjgjkhkk566546")
					.titulo("Gramatica I")
					.editorial(editorialService.save(editorial))
					.autores(Set.of(autorService.save(autor)))
					.build();


			Autor autor2 = Autor.builder()
					.apellido("Juarex")
					.nombre("Jhon")
					.build();


			Libro ingles = Libro.builder()
					.tema("ingles")
					.titulo("Good news")
					.editorial(editorialService.save(editorial2))
					.isbn("sasasasass")
					.autores(Set.of(autorService.findById(1L).get(),autorService.save(autor2)))
					.build();

			libroService.saveAll(List.of(libro, ingles));

		};


	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/api/v1/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET","POST","PUT","DELETE")
						.allowedHeaders("*");
			}
		};
	}




}
