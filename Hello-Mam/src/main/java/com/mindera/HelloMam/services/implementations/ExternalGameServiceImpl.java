package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.clients.ExternalGameClient;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.services.interfaces.ExternalGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mindera.HelloMam.enums.MediaType.GAME;

@Service
public class ExternalGameServiceImpl implements ExternalGameService {

    private final ExternalGameClient externalGameClient;
    private final MediaServiceImpl mediaService;
    private final MediaRepository mediaRepository;

    @Autowired
    public ExternalGameServiceImpl(ExternalGameClient externalGameClient, MediaServiceImpl mediaService, MediaRepository mediaRepository) {
        this.externalGameClient = externalGameClient;
        this.mediaService = mediaService;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public List<ExternalGame> getAllVideogames() throws RefIdNotFoundException {
        List<ExternalGame> games = externalGameClient.getAllVideogames();
        checkIfExistsAndSave(games);
        return games;
    }

    @Override
    public ExternalGame getVideogameById(int id) {
        return externalGameClient.getVideogameById(id);
    }

    @Override
    public List<ExternalGame> getGameByTitle(String title) throws RefIdNotFoundException {
        List<ExternalGame> games = externalGameClient.getGameByTitle(title);
        checkIfExistsAndSave(games);
        return games;
    }

    @Override
    public List<ExternalGame> getGameRecommendations(int id) throws RefIdNotFoundException {
        List<ExternalGame> games = externalGameClient.getGameRecommendations(id);
        checkIfExistsAndSave(games);
        return games;
    }

    @Override
    public List<ExternalGame> getGameByGenre(String genre) throws RefIdNotFoundException {
        List<ExternalGame> games = externalGameClient.getGameByGenre(genre);
        checkIfExistsAndSave(games);
        return games;
    }

    @Override
    public List<ExternalGame> getTopFiveVideoGames() throws RefIdNotFoundException {
        List<ExternalGame> games = externalGameClient.getTopFiveVideoGames();
        checkIfExistsAndSave(games);
        return games;
    }

    private void checkIfExistsAndSave(List<ExternalGame> games) throws RefIdNotFoundException {
        for (ExternalGame game : games) {
            if (mediaRepository.findByRefId(game.getIgdbId()).isEmpty()) {
                mediaService.createGame(game);
            }
        }
    }
}