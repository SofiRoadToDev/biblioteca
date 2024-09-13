package com.sofi.biblioteca.entities;

import jakarta.annotation.Generated;
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
@Table(name="libros")
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank(message = "EL titulo es obligatorio")
    @Size(max = 100, message = "Debe tener un máximo de 100 caracteres")
    private String titulo;

    @ManyToOne(cascade = { CascadeType.MERGE}) // Merge para persisitir por separado, asi comprobamos que no duplicamos la editorial
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable( name = "autores_libros",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores = new HashSet<>();

    @Column(length = 100)
    @NotBlank(message = "EL ISBN es obligatorio")
    @Size(max = 100, message = "Debe tener un máximo de 100 caracteres")
    private String isbn;

    @Column(length = 100)
    @Size(max = 100, message = "Debe tener un máximo de 100 caracteres")
    private String tema;


}
