package com.sofi.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="editoriales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "editorial")
    private Set<Libro> libros = new HashSet<>();
}
