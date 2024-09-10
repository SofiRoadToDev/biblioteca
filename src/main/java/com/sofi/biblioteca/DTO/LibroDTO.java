package com.sofi.biblioteca.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDTO {
    private Long id;
    private String titulo;
    private EditorialDTO editorial;
    private String isbn;
    private String tema;
}
