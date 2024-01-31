package com.mindera.converters;

import com.mindera.deprecated.create.VideogameCreateDto;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;

public class VideogameConverter {

    public static Videogame fromCreateDtoToEntity(VideogameCreateDto videogameCreateDto){
        return Videogame.builder()
                .build();
    }

    public static VideogameGetDto fromEntityToGetDto(Videogame videogame){
        return new VideogameGetDto(videogame.getIgdbId(), videogame.getName(), videogame.getReleaseDate(), videogame.getRating(), videogame.getGenreIds());
    }
}