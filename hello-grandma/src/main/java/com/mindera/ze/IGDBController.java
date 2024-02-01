package com.mindera.ze;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindera.dtos.get.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.ze.IGDBRepository;
import com.mindera.ze.IGDBService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/igdb")
@RegisterRestClient(configKey="igdb-api")
public class IGDBController implements IGDBRepository {

    @Inject
    IGDBService igdbService;

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
    @Path("/videogames/developer/{developer}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VideogameGetDto> getVideogameByDeveloper(@PathParam("developer") String developer){
        return igdbService.findByDeveloper(developer);
    }
}
