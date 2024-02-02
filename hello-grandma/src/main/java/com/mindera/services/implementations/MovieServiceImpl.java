package com.mindera.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.converters.MovieConverter;
import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.entities.MovieExtension;
import com.mindera.enums.MovieGenres;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.repositories.MovieRepository;
import com.mindera.services.interfaces.MovieService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mindera.converters.MovieConverter.fromEntityToGetDto;
import static com.mindera.enums.MovieGenres.getMovieGenreByName;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {
    @Inject
    MovieRepository movieRepository;

    @Inject
    MovieExtensionServiceImpl movieExtensionService;

    @Override
    public Movie findByMongoId(String id) throws MovieNotFoundException {
        return movieRepository.findByIdOptional(new ObjectId(id)).orElseThrow(()->
                new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    @Override
    public Movie findByTmdbId(Long id) throws MovieNotFoundException {
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
            //throw new MovieNotFoundException("Movie with title " + title + " not found");
            tempList = movieExtensionService.findMovieByTitle();
        }
        return tempList;
    }

    @Override
    public List<MovieGetDto> getAllMovies() {
        return movieRepository.listAll().stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @Override
    public Movie getMovieDetailsByTmdbId(String tmdbId) throws JsonProcessingException {
        Movie movie = movieExtensionService.getMovieDetailsByTmdbId(tmdbId);
        convertFromObjectListToStringList(movie);
        return movie;
    }

    private void convertFromObjectListToStringList(Movie movie) {
        List<Object> objectList = movie.getExcludedGenres();
        for (Object object : objectList) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.convertValue(object, JsonNode.class);
            movie.getGenres().add(jsonNode.get("name").asText());
        }
    }

    @Override
    public List<MovieGetDto> getMovieRecommendation(Integer movieId) throws JsonProcessingException {
        List<Movie> movies = fromMovieExtensionListToMovieList(movieExtensionService.getMovieRecommendation(movieId));
        checkIfExistsAndAddToMongoDb(movies);
        return movies.stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @Override
    public List<MovieGetDto> discoverMovies(String genres) throws InvalidGenreException, JsonProcessingException {
        String genreId = convertGenreStringToGenreId(genres);
        List<Movie> movies = fromMovieExtensionListToMovieList(movieExtensionService.discoverMovies(genreId));
        checkIfExistsAndAddToMongoDb(movies);
        return movies.stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    private void checkIfExistsAndAddToMongoDb(List<Movie> movies) {
        for(Movie movie : movies) {
            if(movieRepository.findByTmdbId(movie.getTmdbId()).isEmpty()) {
                movieRepository.persist(movie);
            }
        }
    }

    @Override
    public void create(Movie movie) {
        movieRepository.persist(movie);
    }

    @Override
    public String convertGenreStringToGenreId(String genres) throws InvalidGenreException {
        MovieGenres movieGenre = getMovieGenreByName(genres)
                .orElseThrow(()-> new InvalidGenreException(genres + " is not a valid genre"));
        return String.valueOf(movieGenre.getId());
    }

    @Override
    public List<Movie> fromMovieExtensionListToMovieList(List<MovieExtension.MovieResponse> movieExtensionList) throws JsonProcessingException {
        List<Movie> temp = new ArrayList<>();
        for(MovieExtension.MovieResponse movieResponse : movieExtensionList) {
            temp.add(getMovieDetailsByTmdbId(String.valueOf(movieResponse.getTmdbId())));
        }
        return temp;
    }
}