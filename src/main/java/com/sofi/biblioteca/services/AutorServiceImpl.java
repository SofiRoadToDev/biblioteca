package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Autor;
import com.sofi.biblioteca.repositories.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AutorServiceImpl implements AutorService{


    private final AutorRepository autorRepository;
    @Override
    public Optional<Autor> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Autor> findAll() {
        return (Set<Autor>) autorRepository.findAll();
    }

    @Override
    public Autor crearAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor editarAutor(Long id) {
        return autorRepository.findById(id).orElseThrow();
    }

    @Override
    public void eliminarAutor(Long id) {
         autorRepository.findById(id).orElseThrow();
    }
}
