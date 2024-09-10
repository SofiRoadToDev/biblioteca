package com.sofi.biblioteca.services;

import com.sofi.biblioteca.DTO.AutorDto;
import com.sofi.biblioteca.entities.Autor;
import com.sofi.biblioteca.exceptions.ResourceNotFoundException;
import com.sofi.biblioteca.mappers.AutorMapper;
import com.sofi.biblioteca.repositories.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AutorServiceImpl implements AutorService{


    private final AutorRepository autorRepository;
    @Override
    public AutorDto findById(Long id) {
        return AutorMapper.INSTANCE.autorToAutorDto( autorRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(this.getClass()+" "+"autor id: "+id+" no hallado")
                )
        );
    }

    @Override
    public Set<AutorDto> findAll() {
        return AutorMapper.INSTANCE.setAutorToAutoresDto(
                (Set<Autor>) autorRepository.findAll()
        );
    }

    @Override
    public AutorDto crearAutor(AutorDto autor) {
        return AutorMapper.INSTANCE.autorToAutorDto(
                autorRepository.save(AutorMapper.INSTANCE.autorDtoToAutor(autor))
        );

    }

    @Override
    public AutorDto editarAutor(Long id) {
        return AutorMapper.INSTANCE.autorToAutorDto(
                autorRepository.findById(id).orElseThrow()
        );
    }

    @Override
    public void eliminarAutor(Long id) {
         autorRepository.findById(id).orElseThrow();
    }
}
