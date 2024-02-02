package com.mindera.HelloMam.externals;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalMovies {
    RestClient restClient = RestClient.create();

    String uriBase = "http://localhost:8080/api/v1/movies";

    public String getAllMovies(){
        return restClient.get()
                .uri(uriBase)
                .retrieve()
                .body(String.class);
    }

    public String getMovieById(String refId){
        return restClient.get()
                .uri(uriBase + "/id/" + refId)
                .retrieve()
                .body(String.class);
    }

    public String getMovieDetails(Integer tmbdId){
        return restClient.get()
                .uri(uriBase + "/details/" + tmbdId)
                .retrieve()
                .body(String.class);
    }

    public String getMovieRecommendations(Integer tmbdId){
        return restClient.get()
                .uri(uriBase + "/recommendations/" + tmbdId)
                .retrieve()
                .body(String.class);
    }

    public String getDiscoverMovies(String genre){
        return restClient.get()
                .uri(uriBase + "/discover/" + genre)
                .retrieve()
                .body(String.class);
    }
}