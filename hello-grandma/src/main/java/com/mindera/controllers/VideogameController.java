package com.mindera.controllers;

import com.mindera.deprecated.create.VideogameCreateDto;
import com.mindera.dtos.VideogameGetDto;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.services.VideogameService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
/*import org.jboss.resteasy.reactive.RestResponse;*/

import java.util.List;

@Path("/api/v1/videogames")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideogameController {

    @Inject
    VideogameService videogameService;

    /*@GET
    public RestResponse<List<VideogameGetDto>> getAllVideogames() {
        return RestResponse.ok(videogameService.getAllVideogames());
    }

    @GET
    @Path("/{id}")
    public RestResponse<VideogameGetDto> getVideogameById(@PathParam("id") String id) throws VideogameNotFoundException, VideogameNotFoundException {
        return RestResponse.ok(videogameService.findById(id));
    }

    @POST
    public RestResponse<VideogameGetDto> create(VideogameCreateDto videogame) {
        return RestResponse.accepted(videogameService.create(videogame));
    }*/
}
