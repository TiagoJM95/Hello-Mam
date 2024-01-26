package com.mindera.converters;

import com.mindera.dtos.create.MovieCreateDto;
import com.mindera.dtos.get.MovieGetDto;
import com.mindera.entities.Movie;

public class MovieConverter {

    public static Movie fromCreateDtoToEntity(MovieCreateDto movieCreateDto){
        return Movie.builder()
                .title(movieCreateDto.title())
                .director(movieCreateDto.director())
                .runtime(movieCreateDto.runtime())
                .year(movieCreateDto.year())
                .genres(movieCreateDto.genres())
                .build();
    }

    public static MovieGetDto fromEntityToGetDto(Movie movie){
        return new MovieGetDto(
                movie.id,
                movie.getTitle(),
                movie.getYear(),
                movie.getRuntime(),
                movie.getGenres(),
                movie.getDirector()
        );
    }
}