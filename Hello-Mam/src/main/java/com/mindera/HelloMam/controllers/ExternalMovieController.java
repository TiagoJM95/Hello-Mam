package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.externals.clients.ExternalMovieClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/movies")
public class ExternalMovieController {

    private final ExternalMovieClient externalMovieClient;

    @Autowired
    public ExternalMovieController(ExternalMovieClient externalMovieClient) {
        this.externalMovieClient = externalMovieClient;
    }
}
