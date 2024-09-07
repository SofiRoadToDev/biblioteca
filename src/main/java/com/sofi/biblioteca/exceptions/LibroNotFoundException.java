package com.sofi.biblioteca.exceptions;

public class LibroNotFoundException extends RuntimeException{

    public LibroNotFoundException(String message) {
        super(message);
    }

    public LibroNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
