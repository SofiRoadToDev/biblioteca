package com.sofi.biblioteca.repositories;

import com.sofi.biblioteca.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends JpaRepository<Autor,Long> {
}
