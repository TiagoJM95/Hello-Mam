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
    List<Videogame> getVideogameById(@HeaderParam("Client-ID") String clientId,
                                     @HeaderParam("Authorization") String authorization,
                                     String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getAllVideogames(@HeaderParam("Client-ID") String clientId,
                                        @HeaderParam("Authorization") String authorization,
                                        String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getVideogameByTitle(@HeaderParam("Client-ID") String clientId,
                                        @HeaderParam("Authorization") String authorization,
                                        String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getVideogameByGenre(@HeaderParam("Client-ID") String clientId,
                                        @HeaderParam("Authorization") String authorization,
                                        String query);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Videogame> getVideogameBySearch(@HeaderParam("Client-ID") String clientId,
                                        @HeaderParam("Authorization") String authorization,
                                        String query);

}
