package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.models.ExternalGame;

import java.util.List;

public interface ExternalGameService {
    List<ExternalGame> getAllVideogames() throws RefIdNotFoundException;
    ExternalGame getVideogameById(int id);
    List<ExternalGame> getGameByTitle(String title) throws RefIdNotFoundException;
    List<ExternalGame> getGameRecommendations(int id) throws RefIdNotFoundException;
    List<ExternalGame> getGameByGenre(String genre) throws RefIdNotFoundException;
    List<ExternalGame> getTopFiveVideoGames() throws RefIdNotFoundException;
}