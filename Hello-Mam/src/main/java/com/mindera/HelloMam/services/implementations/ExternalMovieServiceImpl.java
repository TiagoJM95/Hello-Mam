package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.clients.ExternalMovieClient;
import com.mindera.HelloMam.externals.models.ExternalMovie;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.services.interfaces.ExternalMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mindera.HelloMam.enums.MediaType.MOVIE;

@Service
public class ExternalMovieServiceImpl implements ExternalMovieService {

    private final ExternalMovieClient externalMovieClient;
    private final MediaServiceImpl mediaService;
    private final MediaRepository mediaRepository;

    @Autowired
    public ExternalMovieServiceImpl(ExternalMovieClient externalMovieClient, MediaServiceImpl mediaService, MediaRepository mediaRepository) {
        this.externalMovieClient = externalMovieClient;
        this.mediaService = mediaService;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public List<ExternalMovie> getAllMovies() throws RefIdNotFoundException {
        List<ExternalMovie> movies = externalMovieClient.getAllMovies();
        checkIfExistsAndSave(movies);
        return movies;
    }

    @Cacheable("movies")
    @Override
    public ExternalMovie getMovieById(Long id) {
        return externalMovieClient.getMovieById(id);
    }

    @Override
    public List<ExternalMovie> getMovieByTitle(String title) throws RefIdNotFoundException {
        List<ExternalMovie> movies = externalMovieClient.getMovieByTitle(title);
        checkIfExistsAndSave(movies);
        return movies;
    }

    @Cacheable("movies")
    @Override
    public List<ExternalMovie> getMovieRecommendations(Integer id) throws RefIdNotFoundException {
        List<ExternalMovie> movies = externalMovieClient.getMovieRecommendations(id);
        checkIfExistsAndSave(movies);
        return movies;
    }

    @Override
    public List<ExternalMovie> getDiscoverMovies(String genre) throws RefIdNotFoundException {
        List<ExternalMovie> movies = externalMovieClient.getDiscoverMovies(genre);
        checkIfExistsAndSave(movies);
        return movies;
    }

    @Cacheable("movies")
    @Override
    public List<ExternalMovie> getTopRatedMovies() throws RefIdNotFoundException {
        List<ExternalMovie> movies = externalMovieClient.getTopRatedMovies();
        checkIfExistsAndSave(movies);
        return movies;
    }

    private void checkIfExistsAndSave(List<ExternalMovie> movies) throws RefIdNotFoundException {
        for (ExternalMovie movie : movies) {
            if (mediaRepository.findByRefId(movie.getTmdbId()).isEmpty()) {
                mediaService.createMovie(movie);
            }
        }
    }
}
