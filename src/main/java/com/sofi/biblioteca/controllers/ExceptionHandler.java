package com.sofi.biblioteca.controllers;


import com.sofi.biblioteca.exceptions.DuplicatedResourceException;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
@Log4j2
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> BadRequestHandler(MethodArgumentNotValidException ex){
        Map<String,String> exceptions = ex.getBindingResult().getAllErrors()
                .stream().collect(Collectors.toMap(
                        e -> e.getObjectName(),
                        e -> e.getDefaultMessage()
                ));
        return ResponseEntity.badRequest().body(exceptions);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?>badArguments( IllegalArgumentException ex){
        return ResponseEntity.badRequest()
                .body(ex.getMessage());    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> libroNotFound(LibroNotFoundException ex){
        log.info(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?>duplicatedResources(DuplicatedResourceException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
