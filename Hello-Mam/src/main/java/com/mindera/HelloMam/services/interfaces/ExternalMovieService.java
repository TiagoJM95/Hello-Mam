package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.models.ExternalMovie;

import java.util.List;

public interface ExternalMovieService {
    List<ExternalMovie> getAllMovies() throws RefIdNotFoundException;
    ExternalMovie getMovieById(Long id);
    List<ExternalMovie> getMovieByTitle(String title) throws RefIdNotFoundException;
    List<ExternalMovie> getMovieRecommendations(Integer id) throws RefIdNotFoundException;
    List<ExternalMovie> getDiscoverMovies(String genre) throws RefIdNotFoundException;
    List<ExternalMovie> getTopRatedMovies() throws RefIdNotFoundException;
}