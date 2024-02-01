package com.mindera.services.implementations;

import com.mindera.converters.MovieConverter;
import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.repositories.MovieRepository;
import com.mindera.services.interfaces.MovieService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mindera.converters.MovieConverter.fromEntityToGetDto;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {
    @Inject
    MovieRepository movieRepository;

    @Override
    public Movie findByMongoId(String id) throws MovieNotFoundException {
        return movieRepository.findByIdOptional(new ObjectId(id)).orElseThrow(()->
                new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    @Override
    public Movie findByTmdbId(Integer id) throws MovieNotFoundException {
        return movieRepository.findByTmdbId(id).orElseThrow(()->
                new MovieNotFoundException("Movie with tmdbId " + id + " not found"));
    }

    @Override
    public MovieGetDto getMovieById(String id) throws MovieNotFoundException {
        return fromEntityToGetDto(findByMongoId(id));
    }

    @Override
    public List<MovieGetDto> getMoviesByTitle(String title) throws MovieNotFoundException {
        List<MovieGetDto> tempList = movieRepository.findByTitle(title).stream().map(MovieConverter::fromEntityToGetDto).toList();
        if(tempList.isEmpty()){
            throw new MovieNotFoundException("Movie with title " + title + " not found");
        }
        return tempList;
    }

    @Override
    public List<MovieGetDto> getAllMovies() {
        return movieRepository.listAll().stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @Override
    public MovieGetDto create(Movie movie) {
        movieRepository.persist(movie);
        return fromEntityToGetDto(movie);
    }
}