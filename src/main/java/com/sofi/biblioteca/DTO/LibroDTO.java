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
public class LibroDTO {
    private Long id;
    private String titulo;
    private String editorial;
    private String isbn;
    private String tema;
    private Set<AutorDtoSimple> autores= new HashSet<>();
}
