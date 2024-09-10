package com.sofi.biblioteca.services;

import com.sofi.biblioteca.DTO.LibroDTO;
import com.sofi.biblioteca.entities.Autor;
import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;
import com.sofi.biblioteca.mappers.LibroMapper;
import com.sofi.biblioteca.repositories.AutorRepository;
import com.sofi.biblioteca.repositories.LibroRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
@Log4j2
public class LibroServiceImpl implements LibroService{

    private LibroRepository libroRep;
    private AutorRepository autorRepository;

    @Override
    public Set<LibroDTO> getAllLibros() {
        return LibroMapper.INSTANCE.setLibroToLibroDTO(
                (Set<Libro>) libroRep.findAll()
        );
    }

    @Override
    public LibroDTO getLibroByTitulo(String titulo) {
      return LibroMapper.INSTANCE.libroToLibroDto(
              libroRep.findByTitulo(titulo)
                      .orElseThrow(
                              () -> new LibroNotFoundException(String.format(" Libro titulo %s no hallado",titulo)))
      );
    }

    @Override
    public LibroDTO getLibroById(Long id) {
        return LibroMapper.INSTANCE.libroToLibroDto(
                libroRep.findById(id)
                        .orElseThrow(() ->
                                new LibroNotFoundException(String.format("Libro id: %s no encontrado", id)))
        );


    }

    @Override
    public Set<LibroDTO> getLibroByTema(String tema) {
        return LibroMapper.INSTANCE.setLibroToLibroDTO(
                libroRep.findByTema(tema)
        );
    }

    @Override
    public LibroDTO getLibroByISBN(String isbn) {
        return LibroMapper.INSTANCE.libroToLibroDto(
                libroRep.findByIsbn(isbn)
                        .orElseThrow(() ->
                                new LibroNotFoundException(String.format("Libro isbn: %s no encontrado", isbn)))
        );

    }

    @Override
    public LibroDTO saveLibro(Libro libro) {
        Set<Autor>autoresEnRepo = (Set<Autor>) autorRepository.findAll();

        libro.getAutores().stream()
                .filter(a -> !autoresEnRepo.contains(a))
                .forEach(a -> autorRepository.save(a));

        Set<Autor> autoresYaExistentes = libro.getAutores().stream()
                .filter(a -> autoresEnRepo.contains(a))
                .collect(Collectors.toSet());

        libro.setAutores(autoresYaExistentes);

        return LibroMapper.INSTANCE.libroToLibroDto(libroRep.save(libro));
    }

    @Override
    public LibroDTO editLibro(Libro libro) {
        if(libro.getId()==null){
            throw new IllegalArgumentException("El id es obligatorio para modificar un libro");
        }
        Libro book = libroRep.findById(libro.getId())
                .orElseThrow(() -> new LibroNotFoundException("Libro id "+libro.getId()+" no encontraddo"));
        book.setTema(libro.getTema());
        book.setIsbn(libro.getIsbn());
        book.setEditorial(libro.getEditorial());
        book.setTitulo(libro.getTitulo());

        Set<Autor>autoresEnRepo = (Set<Autor>) autorRepository.findAll();

        libro.getAutores().stream()
                .filter(a -> !autoresEnRepo.contains(a))
                .forEach(a -> autorRepository.save(a));

        Set<Autor> autoresYaExistentes = libro.getAutores().stream()
                .filter(a -> autoresEnRepo.contains(a))
                .collect(Collectors.toSet());

        book.setAutores(autoresYaExistentes);

        return LibroMapper.INSTANCE.libroToLibroDto(libroRep.save(book));
    }

    @Override
    public void deleteLibro(Long id) {
        Libro libro = libroRep.findById(id)
                .orElseThrow(() -> new LibroNotFoundException(String.format("Libro id: %s no encontrado", id)));
        if(libro == null){
            throw new RuntimeException("Libro no encontrado");
        }
        libroRep.delete(libro);
    }

    @Override
    public Set<Libro> getByPalabraEnTitulo(String palabra) {
        return libroRep.getLibrosConPalabraEnTitulo(palabra);
    }
}
