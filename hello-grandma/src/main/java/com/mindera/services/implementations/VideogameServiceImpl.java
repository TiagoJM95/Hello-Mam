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
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

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
    public List<VideogameGetDto> getAllVideogames(){
        return videogameRepository.listAll().stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    //Vai buscar ao IGDB
    public List<VideogameGetDto> getAllVideogamesFromIGDB() {
        return videogameExtensionClient.getAllVideogames(clientId, token.toString(), "").stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    /*public VideogameGetDto create(VideogameCreateDto videogame){
        Videogame videogameEntity = VideogameConverter.fromCreateDtoToEntity(videogame);
        videogameRepository.persist(videogameEntity);
        return VideogameConverter.fromEntityToGetDto(videogameEntity);
    }*/

    //Procura no Mongo
    @CacheResult(cacheName = "games")
    public VideogameGetDto findByIgdbId(String id) throws VideogameNotFoundException {
        Videogame videogame = videogameRepository.findByIdOptional(new ObjectId(id)).orElseThrow(()->
                new VideogameNotFoundException(VIDEOGAME_NOT_FOUND));
        return VideogameConverter.fromEntityToGetDto(videogame);
    }

    //Procura no IGDB
    @CacheResult(cacheName = "games")
    public Videogame findByIdFromIGDB(String id) throws VideogameNotFoundException {
        List<Videogame> videogames = videogameExtensionClient.getVideogameById(clientId, "Bearer " + token.getAccess_token(), "fields *; where id = " + id + ";");
        if (videogames.isEmpty()) {
            throw new VideogameNotFoundException(VIDEOGAME_NOT_FOUND);
        }
        return videogames.getFirst();
    }



    @CacheResult(cacheName = "games")
    public List<VideogameGetDto> findByTitle(String title) {
        List <VideogameGetDto> videogames = null;
        for (Videogame videogame : videogameRepository.listAll()) {
            if (videogame.getName().toLowerCase().contains(title.toLowerCase())) {
                videogames.add(VideogameConverter.fromEntityToGetDto(videogame));
            }
        }
        return videogames;
    }

    @CacheResult(cacheName = "games")
    public List<VideogameGetDto> findBySearch(String title) throws VideogameNotFoundException {
        List<Videogame> foundVideogames = videogameExtensionClient.getVideogameBySearch(clientId, "Bearer " + token.getAccess_token(), "search \"" + title + "\";");
        List<Videogame> addedVideogames = new ArrayList<>();
        for (Videogame videogame : foundVideogames) {
            addedVideogames.add(findByIdFromIGDB(String.valueOf(videogame.getIgdbId())));
            persist(findByIdFromIGDB(String.valueOf(videogame.getIgdbId())));
        }
        return addedVideogames.stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }

    @CacheResult(cacheName = "games")
    public List<VideogameGetDto> findByGenre(int genre) {
        return videogameExtensionClient.getVideogameByGenre(clientId, token.getAccess_token(), "fields *; where genres = " + genre + ";").stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }


}