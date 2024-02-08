package com.mindera.services.implementations;

import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.repositories.MovieRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class MovieServiceImplTest {

    @InjectMock
    MovieRepository movieRepository;
    @Inject
    MovieServiceImpl movieService;

    private List<Movie> movies = new ArrayList<>();
    private Movie movie;
    private Movie movie2;


    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setTmdbId(60135L);
        movie.setTitle("Hello Grandma");
        movie.setReleaseDate("2024-02-01");
        movie.setVoteAverage(9.5);
        movie.setOverview("overview");
        movie.setStatus("status");
        movie.setTagline("tagline");
        movie.setRuntime(120);
        movie.setOriginalLanguage("english");
        movie.setRevenue("revenue");
        movie.setBudget("1000000");
        movie.setPopularity("popularity");
        movie.setVoteCount("1000");
        movie.setGenres(List.of("drama", "biography"));

        movie2 = new Movie();
        movie2.setTmdbId(60975L);
        movie2.setTitle("Hello Mam");
        movie2.setReleaseDate("2023-11-05");
        movie2.setVoteAverage(8.9);
        movie2.setOverview("overview");
        movie2.setStatus("status");
        movie2.setTagline("tagline");
        movie2.setRuntime(90);
        movie2.setOriginalLanguage("english");
        movie2.setRevenue("revenue");
        movie2.setBudget("3000000");
        movie2.setPopularity("popularity");
        movie2.setVoteCount("10000");
        movie2.setGenres(List.of("romance", "comedy"));

        movies.add(movie);
        movies.add(movie2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByTmdbId() throws MovieNotFoundException {

        when(movieRepository.findByTmdbId(movie.getTmdbId())).thenReturn(Optional.of(movie));

        Movie movieTest = movieService.findByTmdbId(movie.getTmdbId());

        assertNotNull(movieTest);
        assertEquals(movie, movieTest);

        verify(movieRepository, times(1)).findByTmdbId(movie.getTmdbId());
    }

    @Test
    void testFindByTmdbId_NotFound() {

        Long wrongId = 1342L;

        when(movieRepository.findByTmdbId(wrongId)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.findByTmdbId(wrongId));

        verify(movieRepository, times(1)).findByTmdbId(wrongId);
    }


    @Test
    void testGetMovieById() throws MovieNotFoundException {

        when(movieRepository.findByTmdbId(movie.getTmdbId())).thenReturn(Optional.of(movie));

        MovieGetDto movieTest = movieService.getMovieById(movie.getTmdbId());

        assertNotNull(movieTest);

        verify(movieRepository, times(1)).findByTmdbId(movie.getTmdbId());
    }

    @Test
    void testGetMovieById_NotFound() {
        Long wrongId = 6532L;

        when(movieRepository.findByTmdbId(wrongId)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById(wrongId));

        verify(movieRepository, times(1)).findByTmdbId(wrongId);
    }

    @Test
    void testGetAllMovies() {
        when(movieRepository.listAll()).thenReturn(movies);

        List<MovieGetDto> movieList = movieService.getAllMovies();

        assertEquals(2, movieList.size());

        verify(movieRepository, times(1)).listAll();
    }

    @Test
    void testCreate() {
        Movie movieTest = new Movie();
        movieService.create(movieTest);
        verify(movieRepository, times(1)).persist(movieTest);
    }

    @Test
    void checkIfExistsAndAddToMongoDb_NotExists() {

        movieService.checkIfExistsAndAddToMongoDb(movies);

        verify(movieRepository, times(2)).findByTmdbId(anyLong());
        assertNotNull(movieRepository.findByTmdbId(60135L));
        assertNotNull(movieRepository.findByTmdbId(60975L));
    }

    @Test
    void checkIfExistsAndAddToMongoDb() {
        MovieServiceImpl movieServiceSpy = spy(movieService);

        when(movieRepository.findByTmdbId(60135L)).thenReturn(Optional.of(movie));

        movieServiceSpy.checkIfExistsAndAddToMongoDb(movies);

        verify(movieRepository, times(2)).findByTmdbId(anyLong());
        assertNotNull(movieRepository.findByTmdbId(movie2.getTmdbId()));
        verify(movieServiceSpy, atMost(1)).create(any(Movie.class));
    }
}
