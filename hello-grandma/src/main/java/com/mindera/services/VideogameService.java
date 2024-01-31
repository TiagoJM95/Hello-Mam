package com.mindera.services;

import com.mindera.converters.VideogameConverter;
import com.mindera.deprecated.create.VideogameCreateDto;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.repositories.VideogameRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mindera.util.Messages.VIDEOGAME_NOT_FOUND;
@ApplicationScoped
public class VideogameService implements VideogameRepository {

    public List<VideogameGetDto> getAllVideogames(){
        return listAll().stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    public VideogameGetDto create(VideogameCreateDto videogame){
        Videogame videogameEntity = VideogameConverter.fromCreateDtoToEntity(videogame);
        persist(videogameEntity);
        return VideogameConverter.fromEntityToGetDto(videogameEntity);
    }

    public VideogameGetDto findById(String id) throws VideogameNotFoundException {
        Videogame videogame = findByIdOptional(new ObjectId(id)).orElseThrow(()->
                new VideogameNotFoundException(VIDEOGAME_NOT_FOUND));
        return VideogameConverter.fromEntityToGetDto(videogame);
    }
}