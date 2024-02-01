package com.mindera.ze;

import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.converters.VideogameConverter;
import com.mindera.dtos.get.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import io.quarkus.cache.CacheResult;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
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

@Getter
@Setter
@ApplicationScoped
public class IGDBService implements IGDBRepository {

    private static final Logger LOG = Logger.getLogger(IGDBService.class);


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
                    Videogame videogame = findById(Integer.parseInt(idString));
                    foundGames.add(videogame);
                    //persist(videogame);
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

        // Get the first Videogame from the list
        Videogame videogame = videogames.get(0);
        return videogame;
    }

    public List<VideogameGetDto> findByDeveloper(String developer) {
        return list("developer", developer).stream().map(VideogameConverter::fromEntityToGetDto).toList();
    }



}
