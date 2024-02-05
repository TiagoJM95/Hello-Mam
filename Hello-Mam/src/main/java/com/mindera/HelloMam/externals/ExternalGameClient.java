package com.mindera.HelloMam.externals;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExternalGameClient {

    RestClient restClient = RestClient.create();

    String uriBase = "http://localhost:8080/api/v1/videogames";

    //@Cacheable("games")
    public List<ExternalGame> getAllVideogames(){
        return restClient.get()
                .uri(uriBase)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Cacheable("games")
    public ExternalGame getGameById(String refId){
        return restClient.get()
                .uri(uriBase + "/videogames/" + refId)
                .retrieve()
                .body(ExternalGame.class);
    }

    @Cacheable("games")
    public ExternalGame getGameDetails(Integer refId){
        return restClient.get()
                .uri(uriBase + "/videogames/" + refId)
                .retrieve()
                .body(ExternalGame.class);
    }

    @Cacheable("games")
    public List<ExternalGame> getGameByTitle(String search){
        return restClient.get()
                .uri(uriBase + "/title/" + search)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Cacheable("games")
    public List<ExternalGame> getGameByGenre(int genre){
        return restClient.get()
                .uri(uriBase + "/genres/" + genre)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Cacheable("games")
    public List<ExternalGame> getGameRecommendations(Integer refId){
        return restClient.get()
                .uri(uriBase + "/recommendations/" + refId)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Cacheable("games")
    public List<ExternalGame> getDiscoverGame(int i) {
        return restClient.get()
                .uri(uriBase + "/discover/" + i)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }
}
