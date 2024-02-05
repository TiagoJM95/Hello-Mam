package com.mindera.services.implementations;

import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.mindera.converters.VideogameConverter;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.clients.VideogameExtensionClient;
import com.mindera.repositories.VideogameRepository;
import com.mindera.services.interfaces.VideogameService;
import io.quarkus.cache.CacheResult;
import io.quarkus.scheduler.Scheduled;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

import static com.mindera.converters.VideogameConverter.fromGetDtoToEntity;
import static com.mindera.util.Messages.VIDEOGAME_NOT_FOUND;
import static io.quarkus.mongodb.panache.PanacheMongoEntityBase.persist;

@ApplicationScoped
public class VideogameServiceImpl implements VideogameService {

    @Inject
    VideogameRepository videogameRepository;

    @Inject
    @RestClient
    VideogameExtensionClient videogameExtensionClient;

    @ConfigProperty(name = "TWITCH_CLIENT_ID")
    String clientId;

    @ConfigProperty(name = "TWITCH_CLIENT_SECRET")
    String clientSecret;

    private TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;

    private TwitchToken token;

    private long tokenExpirationTime;

    @PostConstruct
    public void init(){
        refreshToken();
    }


    public void refreshToken() {
        token = tAuth.requestTwitchToken(clientId, clientSecret);
        tokenExpirationTime = System.currentTimeMillis() + token.getExpires_in() * 1000;
    }

    @Scheduled(every = "1m") // checks every minute
    public void refreshTokenIfNeeded() {
        // Refresh the token 1 minute before it expires
        if (System.currentTimeMillis() > tokenExpirationTime - 60000) {
            refreshToken();
        }
    }

    //Vai buscar ao Mongo
    public List<VideogameGetDto> getAllVideogamesInMongo(){
        return videogameRepository.listAll().stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    //Vai buscar ao IGDB
    public List<VideogameGetDto> getAllVideogamesFromIGDB() {
        List<Videogame> foundVideogames = videogameExtensionClient.getAllVideogamesInIGDB(clientId, token.toString(), "");
        for (Videogame videogame : foundVideogames) {
            if(videogameRepository.findByIgdbId(videogame.getIgdbId()).isEmpty()){
                persist(videogame);
            }
        }
        return videogameExtensionClient.getAllVideogamesInIGDB(clientId, token.toString(), "").stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    //Procura no Mongo
    @CacheResult(cacheName = "games")
    public VideogameGetDto findByIdInMongo(String id) throws VideogameNotFoundException {
        Videogame videogame = videogameRepository.findByIgdbId(Integer.parseInt(id)).orElseThrow(()-> new VideogameNotFoundException(VIDEOGAME_NOT_FOUND));
        return VideogameConverter.fromEntityToGetDto(videogame);
    }

    //Procura no IGDB
    @CacheResult(cacheName = "games")
    public Videogame findByIdInIGDB(String id) throws VideogameNotFoundException {
        List<Videogame> videogames = videogameExtensionClient.getVideogameByIdInIGDB(clientId, "Bearer " + token.getAccess_token(), "fields *; where id = " + id + ";");
        if (videogames.isEmpty()) {
            throw new VideogameNotFoundException(VIDEOGAME_NOT_FOUND);
        }
        return videogames.getFirst();
    }



    @CacheResult(cacheName = "games")
    public List<VideogameGetDto> findByTitleInMongo(String title) {
        List <VideogameGetDto> videogames = new ArrayList<>();
        for (Videogame videogame : videogameRepository.listAll()) {
            if (videogame.getName().toLowerCase().contains(title.toLowerCase())) {
                videogames.add(VideogameConverter.fromEntityToGetDto(videogame));
            }
        }
        return videogames;
    }

    @CacheResult(cacheName = "games")
    public List<VideogameGetDto> findBySearchInIGDB(String title) throws VideogameNotFoundException {
        String query = "search \"" + title + "\";";
        List<Videogame> foundVideogames = videogameExtensionClient.getVideogameBySearchInIGDB(clientId, "Bearer " + token.getAccess_token(), query);
        List<Videogame> addedVideogames = new ArrayList<>();
        for (Videogame videogame : foundVideogames) {
            addedVideogames.add(findByIdInIGDB(String.valueOf(videogame.getIgdbId())));
            persist(findByIdInIGDB(String.valueOf(videogame.getIgdbId())));
        }
        return addedVideogames.stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    @CacheResult(cacheName = "games")
    public List<VideogameGetDto> findByGenreInIGDB(int genre) {
        return videogameExtensionClient.getVideogameByGenreInIGDB(clientId, token.getAccess_token(), "fields *; where genres = " + genre + ";").stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    public List<VideogameGetDto> getGameRecommendations(int gameId) throws VideogameNotFoundException {
        Videogame game = fromGetDtoToEntity(findByIdInMongo(String.valueOf(gameId)));
        List<Videogame> recommendedGames = new ArrayList<>();
        for (String id : game.getSimilarGames()) {
            recommendedGames.add(findByIdInIGDB(String.valueOf(id)));
        }
        return recommendedGames.stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }


}