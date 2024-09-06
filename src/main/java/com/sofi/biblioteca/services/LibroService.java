package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Libro;

import java.util.Optional;
import java.util.Set;

public interface LibroService {

    public Set<Libro> getAllLibros();
    public Optional<Libro> getLibroByTitulo(String titulo);
    public Optional<Libro> getLibroById(Long id);

    public Set<Libro> getLibroByTema(String tema);
    public Optional<Libro> getLibroByISBN(String isbn);
    public Libro saveLibro (Libro libro);
    public Libro editLibro (Libro libro);
    public void deleteLibro (Long id);
    public Set<Libro> getByPalabraEnTitulo(String palabra);
}
