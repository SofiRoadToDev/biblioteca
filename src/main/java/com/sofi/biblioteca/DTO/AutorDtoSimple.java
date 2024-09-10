package com.sofi.biblioteca.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AutorDtoSimple {

    private Long id;
    private String nombre;
    private String apellido;
}
