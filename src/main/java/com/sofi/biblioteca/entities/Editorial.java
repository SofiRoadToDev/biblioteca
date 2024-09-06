package com.sofi.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Column(length = 100)
    @Size(max = 100)
    @NotBlank(message = "EL nombre es obligatorio")
    private String nombre;

    @OneToMany(mappedBy = "editorial", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Libro> libros = new HashSet<>();
}
