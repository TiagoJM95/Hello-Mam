package com.mindera.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.services.implementations.VideogameServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/v1/videogames")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideogameController{

    @Inject
    VideogameServiceImpl videogameService;

    @GET
    @Path("/{id}")
    public RestResponse<VideogameGetDto> findByIgdbId(@PathParam("id") String id) throws VideogameNotFoundException {
        return RestResponse.ok(videogameService.findByIgdbId(id));
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<Videogame> findById(@PathParam("id") String id) throws VideogameNotFoundException {
        return RestResponse.ok(videogameService.findByIdFromIGDB(id));
    }

    @GET
    @Path("/title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<List<VideogameGetDto>> findByTitle(@PathParam("title") String title) throws VideogameNotFoundException {
        return RestResponse.ok(videogameService.findBySearch(title));
    }

    @GET
    public RestResponse<List<VideogameGetDto>> getAllVideogames(){
        return RestResponse.ok(videogameService.getAllVideogames());
    }




    @GET
    @Path("/genres/{genre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VideogameGetDto> getVideogameByGenre(@PathParam("genre") int genre) throws VideogameNotFoundException, JsonProcessingException {
        return videogameService.findByGenre(genre);
    }



}
