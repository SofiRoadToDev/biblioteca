package com.sofi.biblioteca.services;

import com.sofi.biblioteca.DTO.AutorDto;
import com.sofi.biblioteca.entities.Autor;

import java.util.Optional;
import java.util.Set;

public interface AutorService {

    AutorDto findById(Long id);
    Set<AutorDto> findAll();
    AutorDto crearAutor(AutorDto autor);
    AutorDto editarAutor(Long id);
    void eliminarAutor(Long id);



}
