package com.mindera.services;

import com.mindera.converters.MovieConverter;
import com.mindera.dtos.create.MovieCreateDto;
import com.mindera.dtos.get.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.repositories.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mindera.util.Messages.MOVIE_NOT_FOUND;

@ApplicationScoped
public class MovieService implements MovieRepository {

    public List<MovieGetDto> getAllMovies(){
        return listAll().stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    public MovieGetDto create(MovieCreateDto movie){
        Movie movieEntity = MovieConverter.fromCreateDtoToEntity(movie);
        persist(movieEntity);
        return MovieConverter.fromEntityToGetDto(movieEntity);
    }

    public MovieGetDto findById(String id) throws MovieNotFoundException {
        Movie movie = findByIdOptional(new ObjectId(id)).orElseThrow(()->
                new MovieNotFoundException(MOVIE_NOT_FOUND));
        return MovieConverter.fromEntityToGetDto(movie);
    }

    @Override
    public List<MovieGetDto> findByDirector(String director) {
        return list("director", director).stream().map(MovieConverter::fromEntityToGetDto).toList();
    }
}