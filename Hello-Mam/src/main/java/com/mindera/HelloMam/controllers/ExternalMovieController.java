package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.clients.ExternalMovieClient;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.externals.models.ExternalMovie;
import com.mindera.HelloMam.services.implementations.ExternalGameServiceImpl;
import com.mindera.HelloMam.services.implementations.ExternalMovieServiceImpl;
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
@RequestMapping("api/v1/movies")
public class ExternalMovieController {

    private final ExternalMovieServiceImpl externalMovieService;

    @Autowired
    public ExternalMovieController(ExternalMovieServiceImpl externalMovieService) {
        this.externalMovieService = externalMovieService;
    }


    @GetMapping("/")
    public ResponseEntity<List<ExternalMovie>> getAllMovies() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getAllMovies());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ExternalMovie> getMovieById(@PathParam("id") Long id){
        return ResponseEntity.ok(externalMovieService.getMovieById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ExternalMovie>> getGameByTitle(@PathParam("title") String title) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getMovieByTitle(title));
    }

    @GetMapping("/recommendations/{id}")
    public ResponseEntity<List<ExternalMovie>> getGameRecommendations(@PathParam("id") int id) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getMovieRecommendations(id));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ExternalMovie>> getGameByGenre(@PathParam("genre") String genre) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getDiscoverMovies(genre));
    }

    @GetMapping("/top")
    public ResponseEntity<List<ExternalMovie>> getTopFiveVideoGames() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getTopRatedMovies());
    }


}
