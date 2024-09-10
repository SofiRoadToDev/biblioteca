package com.sofi.biblioteca.mappers;

import com.sofi.biblioteca.DTO.AutorDtoSimple;
import com.sofi.biblioteca.DTO.EditorialDTO;
import com.sofi.biblioteca.DTO.LibroDTO;
import com.sofi.biblioteca.entities.Autor;
import com.sofi.biblioteca.entities.Editorial;
import com.sofi.biblioteca.entities.Libro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {

})
public interface LibroMapper {

      LibroMapper INSTANCE = Mappers.getMapper(LibroMapper.class);

       LibroDTO libroToLibroDto(Libro libro);

       Libro libroDtoToLibro(LibroDTO libroDTO);

       EditorialDTO editorialToEditorialDto(Editorial editorial);
       Editorial editorialDtoToEditorial(EditorialDTO editorialDTO);

       Set<Libro> setLibroDtoToLibro(Set<LibroDTO>libroDTOS);
       Set<LibroDTO>setLibroToLibroDTO(Set<Libro>libros);

       Set<AutorDtoSimple> autorToAutorDto(Set<Autor> autores);
       Set<Autor> autorDtoToAutor(Set<AutorDtoSimple> autorDtoSimple);
}
