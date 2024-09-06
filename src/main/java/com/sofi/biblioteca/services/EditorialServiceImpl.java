package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Editorial;
import com.sofi.biblioteca.repositories.EditorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class EditorialServiceImpl implements EditorialService{

    private final EditorialRepository repository;


    @Override
    public Optional<Editorial> findById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("id s% no encontrado", id))));
    }

    @Override
    public Optional<Editorial> findByName(String nombre) {
        return repository.findByName(nombre);
    }


    @Override
    public Set<Editorial> getAll() {
        return null;
    }

    @Override
    public Editorial createEditorial(Editorial editorial) {
        return null;
    }

    @Override
    public Editorial editEditorial(Long id) {
        return null;
    }

    @Override
    public void deleteEditorial(Long id) {

    }
}
