package com.mindera.services.interfaces;

import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.GameGenreNotFoundException;
import com.mindera.exceptions.videogame.VideogameNotFoundException;

import java.util.List;

public interface VideogameService {

    Videogame findByIgdbId(int id) throws VideogameNotFoundException;

    VideogameGetDto getGameById(int id) throws VideogameNotFoundException;

    List<VideogameGetDto> getGamesByTitle(String title);

    List<VideogameGetDto> getAllGames();

    List<VideogameGetDto> getGameRecommendation(int igdbId) throws GameGenreNotFoundException ;

    List<VideogameGetDto> discoverGames(String genres) throws GameGenreNotFoundException;

    List<VideogameGetDto> getTopFiveVideoGames();

    void create(Videogame videogame);
}
