package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;
import com.sofi.biblioteca.repositories.LibroRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
@Log4j2
public class LibroServiceImpl implements LibroService{

    private LibroRepository libroRep;

    @Override
    public Set<Libro> getAllLibros() {
        return StreamSupport
                .stream(libroRep.findAll().spliterator(),false)
                .collect(Collectors.toSet());
    }

    @Override
    public Libro getLibroByTitulo(String titulo) throws LibroNotFoundException {
      return libroRep.findByTitulo(titulo)
              .orElseThrow(() -> new LibroNotFoundException(String.format(" Libro titulo %s no hallado",titulo)));
    }

    @Override
    public Optional<Libro> getLibroById(Long id) throws LibroNotFoundException {
        Optional<Libro> libro = libroRep.findById(id);
        if (libro.isEmpty()){
          throw new LibroNotFoundException(String.format("Libro %s no encontrado",id));
        }
        return libro;

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
