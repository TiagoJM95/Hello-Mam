package com.mindera.services.implementations;

import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.mindera.converters.GameConverter;
import com.mindera.dtos.GameGetDto;
import com.mindera.entities.Game;
import com.mindera.enums.GameGenres;
import com.mindera.exceptions.game.GameGenreNotFoundException;
import com.mindera.exceptions.game.GameNotFoundException;
import com.mindera.clients.GameExtensionClient;
import com.mindera.repositories.GameRepository;
import com.mindera.services.interfaces.GameService;
import io.quarkus.scheduler.Scheduled;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

import static com.mindera.converters.GameConverter.fromEntityToGetDto;
import static com.mindera.enums.GameGenres.getGameGenreByName;
import static com.mindera.util.Messages.VIDEOGAME_NOT_FOUND;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    @RestClient
    GameExtensionClient gameExtensionClient;

    @ConfigProperty(name = "TWITCH_CLIENT_ID")
    String clientId;

    @ConfigProperty(name = "TWITCH_CLIENT_SECRET")
    String clientSecret;

    private final TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;

    private TwitchToken token;

    private long tokenExpirationTime;

    @PostConstruct
    public void init() {
        refreshToken();
    }

    public void refreshToken() {
        token = tAuth.requestTwitchToken(clientId, clientSecret);
        tokenExpirationTime = System.currentTimeMillis() + token.getExpires_in() * 1000;
    }

    @Scheduled(every = "1m")
    public void refreshTokenIfNeeded() {
        if (System.currentTimeMillis() > tokenExpirationTime - 60000) {
            refreshToken();
        }
    }

    @Override
    public List<GameGetDto> getAllGames() {
        return gameRepository.listAll().stream().map(GameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public Game findByIgdbId(int id) throws GameNotFoundException {
        return gameRepository.findByIgdbId(id).orElseThrow(()
                -> new GameNotFoundException(VIDEOGAME_NOT_FOUND));
    }

    @Override
    public GameGetDto getGameById(int id) throws GameNotFoundException {
        return fromEntityToGetDto(findByIgdbId(id));
    }

    @Override
    public List<GameGetDto> getGamesByTitle(String title) {
        String query = "search \""+title+"\"; fields *;";
        List<Game> igdbList = gameExtensionClient.getGames(
                clientId, "Bearer " + token.getAccess_token(), query);
        List<Game> mongoList = gameRepository.findByTitle(title);
        if(igdbList.size() > mongoList.size()){
            for(Game game : igdbList) {
                if(gameRepository.findByIgdbId(game.getIgdbId()).isEmpty()) {
                    gameRepository.persist(game);
                }
            }
            return igdbList.stream().map(GameConverter::fromEntityToGetDto).toList();
        }

        return mongoList.stream().map(GameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public List<GameGetDto> getGameRecommendations(int igdbId) {
        String query = "fields *; where similar_games = (" + igdbId + ");";
        List<Game> games = gameExtensionClient.getGames(
                clientId, "Bearer " + token.getAccess_token(), query);
        checkIfExistsAndAddToMongoDb(games);
        return games.stream().map(GameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public List<GameGetDto> getGamesByGenre(String genre) throws GameGenreNotFoundException {
        GameGenres gameGenre = getGameGenreByName(genre).orElseThrow(()-> new GameGenreNotFoundException("Genre not found"));
        String query = "fields *; where genres = (" + gameGenre.getId() + ");";
        List<Game> games = gameExtensionClient.getGames(
                clientId, "Bearer " + token.getAccess_token(), query);
        checkIfExistsAndAddToMongoDb(games);
        return games.stream().map(GameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public List<GameGetDto> getTopRatedGames() {
        String query = "fields *; sort rating desc; limit 5;";
        List<Game> games = gameExtensionClient.getGames(
                clientId, "Bearer " + token.getAccess_token(), query);
        checkIfExistsAndAddToMongoDb(games);
        return games.stream().map(GameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public void create(Game game) {
        gameRepository.persist(game);
    }

    private void checkIfExistsAndAddToMongoDb(List<Game> games) {
        for(Game game : games) {
            if(gameRepository.findByIgdbId(game.getIgdbId()).isEmpty()) {
                create(game);
            }
        }
    }
}