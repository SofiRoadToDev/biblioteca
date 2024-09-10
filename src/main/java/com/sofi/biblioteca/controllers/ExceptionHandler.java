package com.sofi.biblioteca.controllers;

import com.sofi.biblioteca.DTO.ExceptionDTO;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
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

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> libroNotFound(LibroNotFoundException ex){
        //log.info(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

}
