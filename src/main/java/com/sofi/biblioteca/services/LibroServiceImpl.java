package com.sofi.biblioteca.services;

import com.sofi.biblioteca.DTO.EditorialDTO;
import com.sofi.biblioteca.DTO.LibroDTO;
import com.sofi.biblioteca.entities.Autor;
import com.sofi.biblioteca.entities.Editorial;
import com.sofi.biblioteca.entities.Libro;
import com.sofi.biblioteca.exceptions.DuplicatedResourceException;
import com.sofi.biblioteca.exceptions.LibroNotFoundException;
import com.sofi.biblioteca.mappers.EditorialMapper;
import com.sofi.biblioteca.mappers.LibroMapper;
import com.sofi.biblioteca.repositories.AutorRepository;
import com.sofi.biblioteca.repositories.EditorialRepository;
import com.sofi.biblioteca.repositories.LibroRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
@Log4j2
public class LibroServiceImpl implements LibroService{

    private LibroRepository libroRep;
    private EditorialRepository editorialRepository;
    private AutorRepository autorRepository;

    @Override
    public Set<LibroDTO> getAllLibros() {
        return LibroMapper.INSTANCE.setLibroToLibroDTO(
                StreamSupport.stream(libroRep.findAll().spliterator(),false)
                        .collect(Collectors.toSet())
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
    public LibroDTO saveLibro(LibroDTO libroDto) {
        Libro libro = LibroMapper.INSTANCE.libroDtoToLibro(libroDto);
        Optional<Libro> existente= libroRep.findByIsbn(libro.getIsbn());
        if(existente.isPresent()){
            throw  new DuplicatedResourceException((String.format("EL libro isbn: %s ya existe", libro.getIsbn())));
        }
        Set<Autor> autores = libro.getAutores();
        Set<Autor>  autoresToSave = new HashSet<>();
        autores.forEach(a -> {
            Optional <Autor> autorDB = autorRepository.findByApellidoIgnoreCaseAndNombreIgnoreCase(a.getApellido(), a.getNombre());
            if(autorDB.isPresent()){
                autoresToSave.add(autorDB.get());
            }else{
                autoresToSave.add( autorRepository.save(a));
            }
        });
        libro.setAutores(autoresToSave);

        Optional<Editorial> existenteEd = editorialRepository.findByName(libro.getEditorial().getNombre());
        if(existenteEd.isPresent()){
            libro.setEditorial(existenteEd.get());
        }else{
            Editorial nuevaEd = editorialRepository.save(libro.getEditorial());
            libro.setEditorial(nuevaEd);
        }


        return LibroMapper.INSTANCE.libroToLibroDto(libroRep.save(libro));
    }
    @Override
    public LibroDTO editLibro(LibroDTO libroDTO) {
        if(libroDTO.getId()==null){
            throw new IllegalArgumentException("El id es obligatorio para modificar un libro");
        }
        Libro book = libroRep.findById(libroDTO.getId())
                .orElseThrow(() -> new LibroNotFoundException("Libro id "+libroDTO.getId()+" no encontraddo"));
        book.setTema(libroDTO.getTema());
        book.setIsbn(libroDTO.getIsbn());
        book.setTitulo(libroDTO.getTitulo());

        Optional<Editorial>storedEditorial = editorialRepository.findByName(libroDTO.getEditorial());
        if(storedEditorial.isPresent()){
            book.setEditorial(storedEditorial.get());
        }else{
            Editorial editorialToSave = new Editorial();
            editorialToSave.setNombre(libroDTO.getEditorial());
            editorialRepository.save(editorialToSave);
        }

        libroDTO.getAutores().forEach(a -> {
            Optional<Autor>stored = autorRepository.findByApellidoIgnoreCaseAndNombreIgnoreCase(a.getApellido(), a.getNombre());
            if(stored.isPresent()){
                if (!book.getAutores().contains(stored.get())){
                    book.getAutores().add(stored.get());
                }
            }else{
                book.getAutores().add(autorRepository.save(stored.get()));
            }
        });

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
