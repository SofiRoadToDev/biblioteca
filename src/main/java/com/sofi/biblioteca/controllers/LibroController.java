package com.sofi.biblioteca.controllers;

import com.sofi.biblioteca.DTO.LibroDTO;
import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;
import com.sofi.biblioteca.services.LibroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/libros")
@AllArgsConstructor
public class LibroController {

    private final LibroService libroService;


    @GetMapping()
    public ResponseEntity<?> getAll(){
       Set<LibroDTO> libros =  libroService.getAllLibros();
       return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws LibroNotFoundException {
        LibroDTO libro = libroService.getLibroById(id);
        return ResponseEntity.ok().body(libro);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<?>getByTitulo(@PathVariable String titulo) throws LibroNotFoundException{
            LibroDTO libro = libroService.getLibroByTitulo(titulo);
            return ResponseEntity.ok().body(libro);
    }

    @PostMapping()
    public ResponseEntity<?> guardarLibro(@RequestBody @Valid Libro libro){
        LibroDTO nuevo = libroService.saveLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable Long id){
        libroService.deleteLibro(id);
        return ResponseEntity.ok().body("Libro borrado correctamente");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?>editarLibro(@RequestBody Libro libro){
        LibroDTO editedLibro = libroService.editLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(editedLibro);
    }

}
