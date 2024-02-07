package com.mindera.HelloMam.externals.clients;

import com.mindera.HelloMam.externals.models.ExternalMovie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExternalMovieClient {
    RestClient restClient = RestClient.create();

    String uriBase = "http://hello-grandma:8080/api/v1/movies";

    //@Cacheable("movies")
    public List<ExternalMovie> getAllMovies(){
        return restClient.get()
                .uri(uriBase)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ExternalMovie>>(){});
    }

    public ExternalMovie getMovieById(Long id){
        return restClient.get()
                .uri(uriBase + "/" + id)
                .retrieve()
                .body(ExternalMovie.class);
    }

    public List<ExternalMovie> getMovieByTitle(String title){
        return restClient.get()
                .uri(uriBase + "/title/" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public List<ExternalMovie> getMovieRecommendations(Integer id){
        return restClient.get()
                .uri(uriBase + "/recommendation/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ExternalMovie>>(){});
    }

    public List<ExternalMovie> getDiscoverMovies(String genre){
        return restClient.get()
                .uri(uriBase + "/genres/" + genre)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public List<ExternalMovie> getTopRatedMovies(){
        return restClient.get()
                .uri(uriBase + "/top")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }
}