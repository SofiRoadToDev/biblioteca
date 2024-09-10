package com.sofi.biblioteca.mappers;

import com.sofi.biblioteca.DTO.EditorialDTO;
import com.sofi.biblioteca.entities.Editorial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EditorialMapper {

    EditorialMapper INSTANCE = Mappers.getMapper(EditorialMapper.class);

    EditorialDTO editorialToEditorialDto(Editorial editorial);
    Editorial editorialDtoToEditorial(EditorialDTO editorialDTO);
}
