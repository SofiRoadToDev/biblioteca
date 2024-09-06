package com.sofi.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotBlank(message = "EL apellido es obligatorio")
    @Size(max = 50, message = "Debe tener un máximo de 50 caracteres")
    private String apellido;

    @Column(length = 50)
    @NotBlank(message = "EL nombre es obligatorio")
    @Size(max = 50, message = "Debe tener un máximo de 50 caracteres")
    private String nombre;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable( name = "autores_libros",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id"))
    private Set<Libro> libros = new HashSet<>();
}
