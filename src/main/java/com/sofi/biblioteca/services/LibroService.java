package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface LibroService {

    public Set<Libro> getAllLibros();
    public Libro getLibroByTitulo(String titulo) throws LibroNotFoundException;
    public Libro getLibroById(Long id) throws LibroNotFoundException;

    public Set<Libro> getLibroByTema(String tema);
    public Libro getLibroByISBN(String isbn);
    public Libro saveLibro (Libro libro);
    public Libro editLibro (Libro libro);
    public void deleteLibro (Long id);
    public Set<Libro> getByPalabraEnTitulo(String palabra);
}
