package com.sofi.biblioteca.repositories;

import com.sofi.biblioteca.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    public Optional<Libro>findByTitulo(String titulo);
    public Optional<Libro>findByIsbn(String isbn);
    public Set<Libro> findByTema(String tema);

    @Query(" select l from Libro l where l.titulo LIKE %?1%")
    Set<Libro> getLibrosConPalabraEnTitulo(String palabra);
}
