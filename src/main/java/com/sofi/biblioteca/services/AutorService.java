package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Autor;

import java.util.Optional;

public interface AutorService {

    Optional<Autor> findById(Long id);
    Iterable<Autor> findAll();
    Autor crearAutor(Autor autor);
    Autor editarAutor(Long id);
    void eliminarAutor(Long id);



}
