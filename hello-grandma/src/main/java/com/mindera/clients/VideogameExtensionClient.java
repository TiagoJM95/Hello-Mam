package com.mindera.clients;

import com.mindera.entities.Videogame;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/")
@RegisterRestClient(configKey="igdb-api")
public interface VideogameExtensionClient {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getVideogameByIdInIGDB(@HeaderParam("Client-ID") String clientId,
                                           @HeaderParam("Authorization") String authorization,
                                           String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getAllVideogamesInIGDB(@HeaderParam("Client-ID") String clientId,
                                           @HeaderParam("Authorization") String authorization,
                                           String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getVideogameByTitleInIGDB(@HeaderParam("Client-ID") String clientId,
                                              @HeaderParam("Authorization") String authorization,
                                              String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getVideogameByGenreInIGDB(@HeaderParam("Client-ID") String clientId,
                                              @HeaderParam("Authorization") String authorization,
                                              String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getVideogameBySearchInIGDB(@HeaderParam("Client-ID") String clientId,
                                               @HeaderParam("Authorization") String authorization,
                                               String query);

}
