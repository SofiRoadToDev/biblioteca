package com.sofi.biblioteca.services;

import com.sofi.biblioteca.DTO.LibroDTO;
import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface LibroService {

    public Set<LibroDTO> getAllLibros();
    public LibroDTO getLibroByTitulo(String titulo) throws LibroNotFoundException;
    public LibroDTO getLibroById(Long id) throws LibroNotFoundException;

    public Set<LibroDTO> getLibroByTema(String tema);
    public LibroDTO getLibroByISBN(String isbn);
    public LibroDTO saveLibro (Libro libro);
    public LibroDTO editLibro (Libro libro);
    public void deleteLibro (Long id);
    public Set<Libro> getByPalabraEnTitulo(String palabra);
}
