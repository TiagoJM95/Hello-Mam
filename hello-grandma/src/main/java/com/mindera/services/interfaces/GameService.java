package com.mindera.services.interfaces;

import com.mindera.dtos.GameGetDto;
import com.mindera.entities.Game;
import com.mindera.exceptions.game.GameGenreNotFoundException;
import com.mindera.exceptions.game.GameNotFoundException;

import java.util.List;

public interface GameService {

    Game findByIgdbId(int id) throws GameNotFoundException;

    GameGetDto getGameById(int id) throws GameNotFoundException;

    List<GameGetDto> getGamesByTitle(String title);

    List<GameGetDto> getAllGames();

    List<GameGetDto> getGameRecommendations(int igdbId) throws GameGenreNotFoundException ;

    List<GameGetDto> getGamesByGenre(String genres) throws GameGenreNotFoundException;

    List<GameGetDto> getTopRatedGames();

    void create(Game game);
}