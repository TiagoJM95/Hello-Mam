package com.mindera.controllers;

import com.mindera.deprecated.create.MusicCreateDto;
import com.mindera.dtos.MusicGetDto;
import com.mindera.exceptions.music.MusicNotFoundException;
import com.mindera.services.MusicService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/v1/musics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MusicController {

    @Inject
    MusicService musicService;

    @GET
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
    }
}