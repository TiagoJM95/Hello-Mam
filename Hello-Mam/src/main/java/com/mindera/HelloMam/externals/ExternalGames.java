package com.mindera.HelloMam.externals;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalGames {

    RestClient restClient = RestClient.create();

    String uriBase = "http://localhost:8080/api/v1/videogames";

    @Cacheable("games")
    public String getAllVideogames(){
        return restClient.get()
                .uri(uriBase + "/videogames")
                .retrieve()
                .body(String.class);
    }

    @Cacheable("games")
    public String getGameById(String refId){
        return restClient.get()
                .uri(uriBase + "/videogames/" + refId)
                .retrieve()
                .body(String.class);
    }

    @Cacheable("games")
    public String getGameDetails(Integer refId){
        return restClient.get()
                .uri(uriBase + "/videogames/" + refId)
                .retrieve()
                .body(String.class);
    }

    @Cacheable("games")
    public String getGameBySearch(String search){
        return restClient.get()
                .uri(uriBase + "/search/" + search)
                .retrieve()
                .body(String.class);
    }

    @Cacheable("games")
    public String getGameByGenre(int genre){
        return restClient.get()
                .uri(uriBase + "/genres/" + genre)
                .retrieve()
                .body(String.class);
    }

    @Cacheable("games")
    public String getGameRecommendations(Integer refId){
        return restClient.get()
                .uri(uriBase + "/recommendations/" + refId)
                .retrieve()
                .body(String.class);
    }

    @Cacheable("games")
    public String getDiscoverGame(int i) {
        return restClient.get()
                .uri(uriBase + "/discover/" + i)
                .retrieve()
                .body(String.class);
    }
}
