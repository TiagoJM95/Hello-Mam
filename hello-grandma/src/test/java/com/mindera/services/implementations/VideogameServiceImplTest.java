package com.mindera.services.implementations;

import com.mindera.dtos.MovieGetDto;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Movie;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.repositories.VideogameRepository;
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
class VideogameServiceImplTest {

    @InjectMock
    VideogameRepository videogameRepository;

    @Inject
    VideogameServiceImpl videogameService;

    private List<Videogame> videogames = new ArrayList<>();

    private Videogame videogame;
    private Videogame videogame2;

    @BeforeEach
    void setUp() {
        videogame = new Videogame();
        videogame.setIgdbId(2357);
        videogame.setName("Grandma");
        videogame.setReleaseDate(20230303L);
        videogame.setRating(8.8);
        videogame.setGenreIds(List.of(12));
        videogame.setSimilarGames(List.of("Mam", "Hello"));

        videogame2 = new Videogame();
        videogame2.setIgdbId(5423);
        videogame2.setName("Mam");
        videogame2.setReleaseDate(20220303L);
        videogame2.setRating(9.8);
        videogame2.setGenreIds(List.of(12));
        videogame2.setSimilarGames(List.of("Grandma", "Hello"));

        videogames.add(videogame);
        videogames.add(videogame2);

    }

    @Test
    void testGetAllGames() {
        when(videogameRepository.listAll()).thenReturn(videogames);

        List<VideogameGetDto> videogameList = videogameService.getAllGames();

        assertEquals(2, videogameList.size());

        verify(videogameRepository, times(1)).listAll();
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
    void testFindByIgdbId() throws VideogameNotFoundException {
        when(videogameRepository.findByIgdbId(videogame.getIgdbId())).thenReturn(Optional.of(videogame));

        Videogame videogameTest = videogameService.findByIgdbId(videogame.getIgdbId());

        assertNotNull(videogameTest);
        assertEquals(videogame, videogameTest);

        verify(videogameRepository, times(1)).findByIgdbId(videogame.getIgdbId());
    }

    @Test
    void testFindByIgdbId_NotFound() {

        int wrongId = 4343;

        when(videogameRepository.findByIgdbId(wrongId)).thenReturn(Optional.empty());

        assertThrows(VideogameNotFoundException.class, () -> videogameService.findByIgdbId(wrongId));

        verify(videogameRepository, times(1)).findByIgdbId(wrongId);
    }

    @Test
    void testGetGameById() throws VideogameNotFoundException {
        when(videogameRepository.findByIgdbId(videogame.getIgdbId())).thenReturn(Optional.of(videogame));

        VideogameGetDto videogameTest = videogameService.getGameById(videogame.getIgdbId());

        assertNotNull(videogameTest);

        verify(videogameRepository, times(1)).findByIgdbId(videogame.getIgdbId());
    }

    @Test
    void testGetGameById_NotFound()  {
        int wrongId = 5435;

        when(videogameRepository.findByIgdbId(wrongId)).thenReturn(Optional.empty());

        assertThrows(VideogameNotFoundException.class, () -> videogameService.getGameById(wrongId));

        verify(videogameRepository, times(1)).findByIgdbId(wrongId);
    }
/*

    @Test
    void testGetGamesByTitle() {
    }

    @Test
    void testGetGameRecommendation() {
    }

    @Test
    void testDiscoverGames() {
    }

    @Test
    void testGetTopFiveVideoGames() {
    }
*/

    @Test
    void testCreate() {
        Videogame videogameTest = new Videogame();
        videogameService.create(videogameTest);
        verify(videogameRepository, times(1)).persist(videogameTest);
    }
}