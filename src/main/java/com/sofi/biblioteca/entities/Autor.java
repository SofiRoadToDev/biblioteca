package com.sofi.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
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

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.MERGE)
    private Set<Libro> libros = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(apellido.toLowerCase(), autor.apellido.toLowerCase()) && Objects.equals(nombre.toLowerCase(), autor.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash( apellido, nombre);
    }
}
