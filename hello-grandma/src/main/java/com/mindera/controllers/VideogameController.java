package com.mindera.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.repositories.VideogameExtensionRepository;
import com.mindera.services.implementations.VideogameExtensionServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/api/v1/videogames")
@RegisterRestClient(configKey="igdb-api")
public class VideogameController implements VideogameExtensionRepository {

    @Inject
    VideogameExtensionServiceImpl igdbService;


    @GET
    @Path("/videogames")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Videogame> getAll() throws VideogameNotFoundException, JsonProcessingException {
        return igdbService.getAll();
    }


    @POST
    @Path("/videogames")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Videogame> getAllVideogames() throws VideogameNotFoundException, JsonProcessingException {
        return igdbService.getAllVideogames();
    }

    @POST
    @Path("/videogames/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Videogame findById(@PathParam("id") String id) throws VideogameNotFoundException, JsonProcessingException {
        return igdbService.findById(Integer.parseInt(id));
    }

    @POST
    @Path("/search/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Videogame> findBySearch(@PathParam("search") String search) throws VideogameNotFoundException, JsonProcessingException {
        return igdbService.findBySearch(search);
    }

    @POST
    @Path("/genres/{genre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Videogame> getVideogameByGenre(@PathParam("genre") int genre) throws VideogameNotFoundException, JsonProcessingException {
        return igdbService.findByGenre(genre);
    }
}
