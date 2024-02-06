package com.mindera.services.implementations;

import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.mindera.converters.VideogameConverter;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.enums.GameGenres;
import com.mindera.exceptions.videogame.GameGenreNotFoundException;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.clients.VideogameExtensionClient;
import com.mindera.repositories.VideogameRepository;
import com.mindera.services.interfaces.VideogameService;
import io.quarkus.scheduler.Scheduled;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

import static com.mindera.converters.VideogameConverter.fromEntityToGetDto;
import static com.mindera.enums.GameGenres.getGameGenreByName;
import static com.mindera.util.Messages.VIDEOGAME_NOT_FOUND;

@ApplicationScoped
public class VideogameServiceImpl implements VideogameService {

    @Inject
    VideogameRepository videogameRepository;

    @Inject
    @RestClient
    VideogameExtensionClient videogameExtensionClient;

    //@ConfigProperty(name = "TWITCH_CLIENT_ID")
    String clientId = "0mvqqdg8tv64n09qhqqhkri7t29clw";

    //@ConfigProperty(name = "TWITCH_CLIENT_SECRET")
    String clientSecret = "dkrrcsvjrgd7b6c8u95ksqcatgtyqt";

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
    public List<VideogameGetDto> getAllGames() {
        return videogameRepository.listAll().stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public Videogame findByIgdbId(int id) throws VideogameNotFoundException {
        return videogameRepository.findByIgdbId(id).orElseThrow(()
                -> new VideogameNotFoundException(VIDEOGAME_NOT_FOUND));
    }

    @Override
    public VideogameGetDto getGameById(int id) throws VideogameNotFoundException {
        return fromEntityToGetDto(findByIgdbId(id));
    }

    @Override
    public List<VideogameGetDto> getGamesByTitle(String title) {
        String query = "search \""+title+"\"; fields *;";
        List<Videogame> igdbList = videogameExtensionClient.getVideogameByTitleInIGDB(
                clientId, "Bearer " + token.getAccess_token(), query);
        List<Videogame> mongoList = videogameRepository.findByTitle(title);
        if(igdbList.size() > mongoList.size()){
            for(Videogame videogame : igdbList) {
                if(videogameRepository.findByIgdbId(videogame.getIgdbId()).isEmpty()) {
                    videogameRepository.persist(videogame);
                }
            }
            return igdbList.stream().map(VideogameConverter::fromEntityToGetDto).toList();
        }

        return mongoList.stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public List<VideogameGetDto> getGameRecommendation(int igdbId) {
        String query = "fields *; where similar_games = (" + igdbId + ");";
        List<Videogame> games = videogameExtensionClient.getVideogameByTitleInIGDB(
                clientId, "Bearer " + token.getAccess_token(), query);
        checkIfExistsAndAddToMongoDb(games);
        return games.stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public List<VideogameGetDto> discoverGames(String genre) throws GameGenreNotFoundException {
        GameGenres gameGenre = getGameGenreByName(genre).orElseThrow(()-> new GameGenreNotFoundException("Genre not found"));
        String query = "fields *; where genres = (" + gameGenre.getId() + ");";
        List<Videogame> games = videogameExtensionClient.getVideogameByTitleInIGDB(
                clientId, "Bearer " + token.getAccess_token(), query);
        checkIfExistsAndAddToMongoDb(games);
        return games.stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public List<VideogameGetDto> getTopFiveVideoGames() {
        String query = "fields *; sort rating desc; limit 5;";
        List<Videogame> games = videogameExtensionClient.getVideogameByTitleInIGDB(
                clientId, "Bearer " + token.getAccess_token(), query);
        checkIfExistsAndAddToMongoDb(games);
        return games.stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    @Override
    public void create(Videogame videogame) {
        videogameRepository.persist(videogame);
    }

    private void checkIfExistsAndAddToMongoDb(List<Videogame> games) {
        for(Videogame game : games) {
            if(videogameRepository.findByIgdbId(game.getIgdbId()).isEmpty()) {
                create(game);
            }
        }
    }
}
