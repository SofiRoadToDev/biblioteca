package com.sofi.biblioteca.services;

import com.sofi.biblioteca.entities.Editorial;

import java.util.Optional;
import java.util.Set;

public interface EditorialService {

    Optional<Editorial> findById(Long id);
    Optional<Editorial>findByName(String nombre);
    Set<Editorial> getAll();
    Editorial createEditorial(Editorial editorial);
    Editorial editEditorial(Long id);
    void deleteEditorial(Long id);


}
