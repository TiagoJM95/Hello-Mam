package com.mindera.controllers;

import com.mindera.services.implementations.MusicServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
/*import org.jboss.resteasy.reactive.RestResponse;*/

@Path("/api/v1/musics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MusicController {

    @Inject
    MusicServiceImpl musicServiceImpl;

   /* @GET
    public RestResponse<List<MusicGetDto>> getAllMusics() {
        return RestResponse.ok(musicService.getAllMusics());
    }

    @GET
    @Path("/{id}")
    public RestResponse<MusicGetDto> getMusicById(@PathParam("id") String id) throws MusicNotFoundException {
        return RestResponse.ok(musicService.findById(id));
    }

    @POST
    public RestResponse<MusicGetDto> create(MusicCreateDto music) {
        return RestResponse.accepted(musicService.create(music));
    }*/
}