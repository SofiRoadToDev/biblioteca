package com.sofi.biblioteca.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="libros")
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Editorial editorial;

    @ManyToMany(mappedBy = "libros")
    private Set<Autor> autores = new HashSet<>();

    private String isbn;

    private String tema;


}
