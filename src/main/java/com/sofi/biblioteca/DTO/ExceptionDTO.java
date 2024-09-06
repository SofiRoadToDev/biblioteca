package com.sofi.biblioteca.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class ExceptionDTO implements Serializable {

    private String message;

    private String field;

    private int statusCode;
}
