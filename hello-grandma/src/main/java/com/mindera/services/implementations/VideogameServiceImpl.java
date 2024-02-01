package com.mindera.services.implementations;

import com.mindera.converters.VideogameConverter;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.repositories.VideogameRepository;
import com.mindera.services.interfaces.VideogameService;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mindera.util.Messages.VIDEOGAME_NOT_FOUND;
@ApplicationScoped
public class VideogameServiceImpl implements VideogameService {

    @Inject
    VideogameRepository videogameRepository;

    public List<VideogameGetDto> getAllVideogames(){
        return videogameRepository.listAll().stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    /*public VideogameGetDto create(VideogameCreateDto videogame){
        Videogame videogameEntity = VideogameConverter.fromCreateDtoToEntity(videogame);
        videogameRepository.persist(videogameEntity);
        return VideogameConverter.fromEntityToGetDto(videogameEntity);
    }*/

    @CacheResult(cacheName = "games")
    public VideogameGetDto findById(String id) throws VideogameNotFoundException {
        Videogame videogame = videogameRepository.findByIdOptional(new ObjectId(id)).orElseThrow(()->
                new VideogameNotFoundException(VIDEOGAME_NOT_FOUND));
        return VideogameConverter.fromEntityToGetDto(videogame);
    }
}