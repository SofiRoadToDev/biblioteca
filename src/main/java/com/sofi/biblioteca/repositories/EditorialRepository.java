package com.sofi.biblioteca.repositories;

import com.sofi.biblioteca.entities.Editorial;
import com.sofi.biblioteca.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface EditorialRepository extends CrudRepository<Editorial,Long> {

    @Query( " Select e from Editorial e where e.nombre = :nombre")
    public Optional<Editorial> findByName(@Param("nombre") String nombre);

}
