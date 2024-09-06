package com.sofi.biblioteca.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(length = 100)
    @Size(max = 100, message = "Debe tener un máximo de 100 caracteres")
    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Editorial editorial;

    @ManyToMany(mappedBy = "libros", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Autor> autores = new HashSet<>();

    @Column(length = 100)
    @Size(max = 100, message = "Debe tener un máximo de 100 caracteres")
    private String isbn;

    @Column(length = 100)
    @Size(max = 100, message = "Debe tener un máximo de 100 caracteres")
    private String tema;


}
