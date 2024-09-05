package com.sofi.biblioteca.controllers;

import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.services.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/libros")
@AllArgsConstructor
public class LibroController {

    private LibroService libroService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
       Set<Libro> libros =  libroService.getAllLibros();
       return ResponseEntity.ok(libros);
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarLibro(@RequestBody Libro libro){
        Libro nuevo = libroService.saveLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable Long id){
        libroService.deleteLibro(id);
        return ResponseEntity.ok().body("Libro borrado correctamente");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?>editarLibro(@RequestBody Libro libro){
        Libro editedLibro = libroService.editLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(editedLibro);
    }

}
