package com.mindera.HelloMam.externals.clients;

import com.mindera.HelloMam.externals.models.ExternalGame;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExternalGameClient {

    RestClient restClient = RestClient.create();

    String uriBase = "http://hello-grandma:8080/api/v1/games";

    //@Cacheable("games")
    public List<ExternalGame> getAllVideogames(){
        return restClient.get()
                .uri(uriBase)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public ExternalGame getVideogameById(int id){
        return restClient.get()
                .uri(uriBase + "/" + id)
                .retrieve()
                .body(ExternalGame.class);
    }

    public List<ExternalGame> getGameByTitle(String title){
        return restClient.get()
                .uri(uriBase + "/title/" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public List<ExternalGame> getGameRecommendations(int id){
        return restClient.get()
                .uri(uriBase + "/recommendation/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public List<ExternalGame> getGameByGenre(String genre){
        return restClient.get()
                .uri(uriBase + "/genres/" + genre)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public List<ExternalGame> getTopFiveVideoGames(){
        return restClient.get()
                .uri(uriBase + "/top")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }
}