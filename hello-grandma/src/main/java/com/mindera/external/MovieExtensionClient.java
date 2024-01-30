package com.mindera.external;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/3")
@RegisterRestClient(configKey="tmdb-api")
public interface MovieExtensionClient {

    @GET
    @Path("/movie/{movie_id}/recommendations")
    JsonObject getMovieRecommendation(@PathParam("movie_id") String movieId,
                                     @HeaderParam("accept") String acceptHeader,
                                     @QueryParam("api_key") String authorizationHeader);

    @GET
    @Path("/find/{external_id}")
    JsonObject getMovieRecommendationByGenre(@PathParam("external_id") String externalId,
                                             @QueryParam("external_source") String externalSource,
                                             @HeaderParam("accept") String acceptHeader,
                                             @QueryParam("api_key") String authorizationHeader);
}