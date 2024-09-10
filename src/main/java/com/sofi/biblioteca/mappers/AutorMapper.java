package com.sofi.biblioteca.mappers;

import com.sofi.biblioteca.DTO.AutorDto;
import com.sofi.biblioteca.DTO.LibroDTO;
import com.sofi.biblioteca.entities.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = LibroMapper.class)
public interface AutorMapper {

    AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);


    AutorDto autorToAutorDto(Autor autor);
    Autor autorDtoToAutor(AutorDto autorDto);

    Set<AutorDto> setAutorToAutoresDto(Set<Autor>autores);
    Set<Autor> setAutorDtoToAutores(Set<AutorDto>autores);


}
