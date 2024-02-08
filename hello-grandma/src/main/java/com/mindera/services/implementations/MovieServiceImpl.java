package com.mindera.services.implementations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.converters.MovieConverter;
import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.entities.MovieExtension;
import com.mindera.enums.MovieGenres;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.clients.MovieExtensionClient;
import com.mindera.repositories.MovieRepository;
import com.mindera.services.interfaces.MovieService;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

import static com.mindera.converters.MovieConverter.fromEntityToGetDto;
import static com.mindera.enums.MovieGenres.getMovieGenreByName;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {
    @Inject
    MovieRepository movieRepository;

    @Inject
    @RestClient
    MovieExtensionClient movieExtensionClient;

    @ConfigProperty(name = "TMDB_API_KEY")
    String apiKey;

    @Override
    public List<MovieGetDto> getAllMovies() {
        return movieRepository.listAll().stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @Override
    public Movie findByTmdbId(Long id) throws MovieNotFoundException {
        return movieRepository.findByTmdbId(id).orElseThrow(()->
                new MovieNotFoundException("Movie with tmdbId " + id + " not found"));
    }

    @Override
    public MovieGetDto getMovieById(Long id) throws MovieNotFoundException {
        return fromEntityToGetDto(findByTmdbId(id));
    }

    @CacheResult(cacheName = "getMoviesByTitle")
    @Override
    public List<MovieGetDto> getMoviesByTitle(String title) {
        List<Movie> tmdbList = fromMovieExtensionListToMovieList(movieExtensionClient.findMovieByTitle(title, "en-US", 1, APPLICATION_JSON, apiKey).getResults());
        List<Movie> mongoList = movieRepository.findByTitle(title);
        if(tmdbList.size() > mongoList.size()){
            for(Movie movie : tmdbList) {
                if(movieRepository.findByTmdbId(movie.getTmdbId()).isEmpty()) {
                    movieRepository.persist(movie);
                }
            }
            return tmdbList.stream().map(MovieConverter::fromEntityToGetDto).toList();
        }
        return mongoList.stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @Override
    public Movie getMovieDetailsByTmdbId(String tmdbId){
        Movie movie = movieExtensionClient.getMovieDetailsByTmdbId(tmdbId, APPLICATION_JSON, apiKey);
        convertFromObjectListToStringList(movie);
        return movie;
    }

    @CacheResult(cacheName = "getMovieRecommendations")
    @Override
    public List<MovieGetDto> getMovieRecommendations(Integer movieId) {
        List<Movie> movies = fromMovieExtensionListToMovieList(movieExtensionClient.getMovieRecommendationByTmdbId(movieId, APPLICATION_JSON, apiKey).getResults());
        checkIfExistsAndAddToMongoDb(movies);
        return movies.stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @CacheResult(cacheName = "getMoviesByGenre")
    @Override
    public List<MovieGetDto> getMoviesByGenre(String genres) throws InvalidGenreException {
        String genreId = convertGenreStringToGenreId(genres);
        List<Movie> movies = fromMovieExtensionListToMovieList(movieExtensionClient.discoverMoviesWithFilters(1, "vote_average.desc", 1000,
                "en", genreId, APPLICATION_JSON, apiKey).getResults());
        checkIfExistsAndAddToMongoDb(movies);
        return movies.stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @CacheResult(cacheName = "getTopRatedMovies")
    @Override
    public List<MovieGetDto> getTopRatedMovies() {
        List<Movie> movies = fromMovieExtensionListToMovieList(movieExtensionClient.getTopRatedMovies(1, "vote_average.desc", 1000,
                "en", APPLICATION_JSON, apiKey).getResults());
        checkIfExistsAndAddToMongoDb(movies);
        return movies.stream().map(MovieConverter::fromEntityToGetDto).toList();
    }

    @Override
    public void create(Movie movie) {
        movieRepository.persist(movie);
    }

    @Override
    public void checkIfExistsAndAddToMongoDb(List<Movie> movies) {
        for(Movie movie : movies) {
            if(movieRepository.findByTmdbId(movie.getTmdbId()).isEmpty()) {
                create(movie);
            }
        }
    }

    @Override
    public void convertFromObjectListToStringList(Movie movie) {
        List<Object> objectList = movie.getExcludedGenres();
        for (Object object : objectList) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.convertValue(object, JsonNode.class);
            movie.getGenres().add(jsonNode.get("name").asText());
        }
    }

    @Override
    public String convertGenreStringToGenreId(String genres) throws InvalidGenreException {
        MovieGenres movieGenre = getMovieGenreByName(genres)
                .orElseThrow(()-> new InvalidGenreException(genres + " is not a valid genre"));
        return String.valueOf(movieGenre.getId());
    }

    @Override
    public List<Movie> fromMovieExtensionListToMovieList(List<MovieExtension.MovieResponse> movieExtensionList){
        List<Movie> temp = new ArrayList<>();
        for(MovieExtension.MovieResponse movieResponse : movieExtensionList) {
            temp.add(getMovieDetailsByTmdbId(String.valueOf(movieResponse.getTmdbId())));
        }
        return temp;
    }
}