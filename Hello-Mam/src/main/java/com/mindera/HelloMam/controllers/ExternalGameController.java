package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.services.implementations.ExternalGameServiceImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/games")
public class ExternalGameController {

    private final ExternalGameServiceImpl externalGameService;

    @Autowired
    public ExternalGameController(ExternalGameServiceImpl externalGameService) {
        this.externalGameService = externalGameService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ExternalGame>> getAllVideogames() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getAllVideogames());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ExternalGame> getVideogameById(@PathParam("id") int id){
        return ResponseEntity.ok(externalGameService.getVideogameById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ExternalGame>> getGameByTitle(@PathParam("title") String title) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getGameByTitle(title));
    }

    @GetMapping("/recommendations/{id}")
    public ResponseEntity<List<ExternalGame>> getGameRecommendations(@PathParam("id") int id) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getGameRecommendations(id));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ExternalGame>> getGameByGenre(@PathParam("genre") String genre) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getGameByGenre(genre));
    }

    @GetMapping("/top")
    public ResponseEntity<List<ExternalGame>> getTopFiveVideoGames() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getTopFiveVideoGames());
    }

}