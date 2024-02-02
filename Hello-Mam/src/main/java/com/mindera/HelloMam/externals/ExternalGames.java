package com.mindera.HelloMam.externals;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalGames {

    RestClient restClient = RestClient.create();

    String uriBase = "http://localhost:8080/api/v1/videogames";

    @Cacheable("games")
    public String getAllGames(){
        return restClient.get()
                .uri(uriBase + "/videogames")
                .retrieve()
                .body(String.class);
    }
}
