package com.mindera.services.implementations;

import com.mindera.dtos.GameGetDto;
import com.mindera.entities.Game;
import com.mindera.exceptions.game.GameNotFoundException;
import com.mindera.repositories.GameRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class GameServiceImplTest {

    @InjectMock
    GameRepository gameRepository;

    @Inject
    GameServiceImpl gameService;

    private List<Game> games = new ArrayList<>();

    private Game game;
    private Game game2;


    @BeforeEach
    void setUp() {

        game = new Game();
        game.setIgdbId(2357);
        game.setName("Grandma");
        game.setReleaseDate(20230303L);
        game.setRating(8.8);
        game.setGenreIds(List.of(12));
        game.setSimilarGames(List.of("Mam", "Hello"));

        game2 = new Game();
        game2.setIgdbId(5423);
        game2.setName("Mam");
        game2.setReleaseDate(20220303L);
        game2.setRating(9.8);
        game2.setGenreIds(List.of(12));
        game2.setSimilarGames(List.of("Grandma", "Hello"));

        games.add(game);
        games.add(game2);
    }

    @Test
    void testGetAllGames() {
        when(gameRepository.listAll()).thenReturn(games);

        List<GameGetDto> gameList = gameService.getAllGames();

        assertEquals(2, gameList.size());

        verify(gameRepository, times(1)).listAll();
    }

    /* @Test
    void testGetAllGames_BadRequest() {

        //invalid genre id
        when(videogameRepository.listAll()).thenReturn(videogames);

        List<VideogameGetDto> videogameList = videogameService.getAllGames();

        assertEquals(2, videogameList.size());

        verify(videogameRepository, times(1)).listAll();
    }*/

    @Test
    void testFindByIgdbId() throws GameNotFoundException {
        when(gameRepository.findByIgdbId(game.getIgdbId())).thenReturn(Optional.of(game));

        Game gameTest = gameService.findByIgdbId(game.getIgdbId());

        assertNotNull(gameTest);
        assertEquals(game, gameTest);

        verify(gameRepository, times(1)).findByIgdbId(game.getIgdbId());
    }

    @Test
    void testFindByIgdbId_NotFound() {

        int wrongId = 4343;

        when(gameRepository.findByIgdbId(wrongId)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.findByIgdbId(wrongId));

        verify(gameRepository, times(1)).findByIgdbId(wrongId);
    }


    @Test
    void testGetGameById() throws GameNotFoundException {
        when(gameRepository.findByIgdbId(game.getIgdbId())).thenReturn(Optional.of(game));

        GameGetDto gameTest = gameService.getGameById(game.getIgdbId());

        assertNotNull(gameTest);

        verify(gameRepository, times(1)).findByIgdbId(game.getIgdbId());
    }

    @Test
    void testGetGameById_NotFound()  {
        int wrongId = 5435;

        when(gameRepository.findByIgdbId(wrongId)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.getGameById(wrongId));

        verify(gameRepository, times(1)).findByIgdbId(wrongId);
    }
/*

    @Test
    void getGamesByTitle() {
    }

    @Test
    void getGameRecommendations() {
    }

    @Test
    void getGamesByGenre() {
    }

    @Test
    void getTopRatedGames() {
    }
*/

    @Test
    void testCreate() {
        Game gameTest = new Game();
        gameService.create(gameTest);
        verify(gameRepository, times(1)).persist(gameTest);
    }
}