package com.sofi.biblioteca.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorDto {
    private Long id;
    private String apellido;
    private String nombre;
    private Set<LibroDTO> libros = new HashSet<>();
}
