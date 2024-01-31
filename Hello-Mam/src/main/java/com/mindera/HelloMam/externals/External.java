package com.mindera.HelloMam.externals;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class External {

    public String testResponse(){
        RestClient restClient = RestClient.create();

        String uriBase = "http://localhost:8080/api/v1/movies";

        return restClient.get()
                .uri(uriBase)
                .retrieve()
                .body(String.class);
    }
}