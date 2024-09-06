package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.repositories.LibroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class LibroServiceImpl implements LibroService{

    private LibroRepository libroRep;

    @Override
    public Set<Libro> getAllLibros() {
        return (Set<Libro>) libroRep.findAll();
    }

    @Override
    public Optional<Libro> getLibroByTitulo(String titulo) {
        return Optional.ofNullable(libroRep.findByTitulo(titulo)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado")));
    }

    @Override
    public Optional<Libro> getLibroById(Long id) {
        return Optional.ofNullable(libroRep.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado")));
    }

    @Override
    public Set<Libro> getLibroByTema(String tema) {
        return libroRep.findByTema(tema);
    }

    @Override
    public Optional<Libro> getLibroByISBN(String isbn) {
        return libroRep.findByIsbn(isbn);
    }

    @Override
    public Libro saveLibro(Libro libro) {
        return libroRep.save(libro);
    }

    @Override
    public Libro editLibro(Libro libro) {
        return libroRep.save(libro);
    }

    @Override
    public void deleteLibro(Long id) {
        Libro libro = libroRep.findById(id).orElse(null);
        if(libro == null){
            throw new RuntimeException("Libro no encontrado");
        }
        libroRep.delete(libro);
    }

    @Override
    public Set<Libro> getByPalabraEnTitulo(String palabra) {
        return libroRep.getLibrosConPalabraEnTitulo(palabra);
    }
}
