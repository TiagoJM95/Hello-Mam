package com.mindera.services.implementations;

import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.converters.VideogameConverter;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.repositories.VideogameExtensionRepository;
import com.mindera.repositories.VideogameRepository;
import io.quarkus.cache.CacheResult;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.quarkus.scheduler.Scheduled;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import static java.lang.Integer.parseInt;

@Getter
@Setter
@ApplicationScoped
public class VideogameExtensionServiceImpl implements VideogameExtensionRepository {

    private static final Logger LOG = Logger.getLogger(VideogameExtensionServiceImpl.class);

    @Inject
    VideogameRepository videogameRepository;

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

    public List<Videogame> getAll(){
        for (Videogame videogame : videogameRepository.listAll()) {
            videogame.setFromIGDB(false);
            videogameRepository.persistOrUpdate(videogame);
        }
        return videogameRepository.listAll();
    }



    @CacheResult(cacheName = "videogames")
    public List<Videogame> getAllVideogames() throws VideogameNotFoundException, JsonProcessingException {

        String url = "https://api.igdb.com/v4/games";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", clientId);
        headers.set("Authorization", "Bearer " + token.getAccess_token());
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {});

        String videogames = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(videogames);

        List<Videogame> foundGames = new ArrayList<>();

        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                if (node.has("id")) {
                    String idString = node.get("id").asText();
                    int id = parseInt(idString);
                    Videogame videogame = videogameRepository.findByIgdbId(id);
                    if (videogame == null) {
                        videogame = findById(id);
                        videogame.setFromIGDB(true);
                        videogameRepository.persist(videogame);
                    }
                    videogame.setFromIGDB(false);
                    foundGames.add(videogame);
                }
            }
        }

        return foundGames;
    }

    public Videogame create(Videogame videogame){
        //persist(videogame);
        return videogame;
    }

    @CacheResult(cacheName = "videogames")
    public Videogame findById(int id) throws VideogameNotFoundException, JsonProcessingException {

        Videogame videogame = videogameRepository.findByIgdbId(id);

        if(videogame != null){
            videogame.setFromIGDB(false);
            return videogame;
        }

        String url = "https://api.igdb.com/v4/games";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", clientId);
        headers.set("Authorization", "Bearer " + token.getAccess_token());

        // Create the query
        String query = "fields *; where id = " + id + ";";
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Videogame> videogames = mapper.readValue(response.getBody(), new TypeReference<List<Videogame>>() {});

        if (videogames == null || videogames.isEmpty()) {
            throw new VideogameNotFoundException("Videogame with id " + id + " not found");
        }

        videogame = videogames.get(0);
        videogame.setFromIGDB(true);
        persist(videogame);

        return videogame;
    }

    @CacheResult(cacheName = "videogames")
    public Videogame findByIgdbId(int igdbId){
        return videogameRepository.findByIgdbId(igdbId);
    }

    public List<Videogame> findBySearch(String search) throws JsonProcessingException, VideogameNotFoundException {

        if (search == null || search.isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }

        String url = "https://api.igdb.com/v4/games";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", clientId);
        headers.set("Authorization", "Bearer " + token.getAccess_token());

        String query = "search \"" + search + "\"; limit 20;";

        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String videogames = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(videogames);

        List<Videogame> foundGames = new ArrayList<>();

        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                if (node.has("id")) {
                    String idString = node.get("id").asText();
                    int id = parseInt(idString);
                    Videogame videogame = videogameRepository.findByIgdbId(id);
                    if (videogame == null) {
                        videogame = findById(id);
                        videogame.setFromIGDB(true);
                    }
                    foundGames.add(videogame);
                }
            }
        }
        for (Videogame videogame : foundGames) {
            if (videogameRepository.findByIgdbId(videogame.getIgdbId()) == null) {
                videogame.setFromIGDB(true);
                videogameRepository.persist(videogame);
            }
        }

        return foundGames;
    }

    public List<Videogame> findByGenre(int genre) throws VideogameNotFoundException, JsonProcessingException {

            String url = "https://api.igdb.com/v4/games";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Client-ID", clientId);
            headers.set("Authorization", "Bearer " + token.getAccess_token());

            // Create the query
            String query = "fields *; where genres = " + genre + ";";
            HttpEntity<String> entity = new HttpEntity<>(query, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            List<Videogame> videogames = mapper.readValue(response.getBody(), new TypeReference<List<Videogame>>() {});

            if (videogames == null || videogames.isEmpty()) {
                throw new VideogameNotFoundException("Videogame with genre " + genre + " not found");
            }

            return videogames;
    }



}
