package com.mindera.HelloMam.externals.clients;

import com.mindera.HelloMam.externals.models.ExternalMovie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExternalMovieClient {
    RestClient restClient = RestClient.create();

    String uriBase = "http://localhost:8080/api/v1/movies";

    public List<ExternalMovie> getAllMovies(){
        return restClient.get()
                .uri(uriBase)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ExternalMovie>>(){});
    }

    public List<ExternalMovie> getMovieById(String refId){
        return restClient.get()
                .uri(uriBase + "/id/" + refId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ExternalMovie>>(){});
    }

    public List<ExternalMovie> getMovieByTitle(String title){
        return restClient.get()
                .uri(uriBase + "/title/" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ExternalMovie>>(){});
    }

    public List<ExternalMovie> getMovieRecommendations(Integer tmbdId){
        return restClient.get()
                .uri(uriBase + "/recommendations/" + tmbdId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ExternalMovie>>(){});
    }

    public List<ExternalMovie> getDiscoverMovies(String genre){
        return restClient.get()
                .uri(uriBase + "/discover/" + genre)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ExternalMovie>>(){});
    }
}