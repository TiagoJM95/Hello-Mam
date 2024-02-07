package com.mindera.clients;

import com.mindera.entities.Game;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v4")
@RegisterRestClient(configKey="igdb-api")
public interface VideogameExtensionClient {
    @POST
    @Path("/games")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    List<Game> getGames(@HeaderParam("Client-ID") String clientId,
                        @HeaderParam("Authorization") String authorization,
                        String query);
}