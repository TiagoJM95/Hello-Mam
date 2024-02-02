package com.mindera.repositories;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/3")
@RegisterRestClient(configKey="tmdb-api")
public interface MovieExtensionRepository {

    @GET
    @Path("/movie/{movie_id}")
    String getMovieById(@PathParam("movie_id") String movieId,
                      @HeaderParam("accept") String acceptHeader,
                      @QueryParam("api_key") String authorizationHeader);

    @GET
    @Path("/movie/{movie_id}/recommendations")
    String getMovieRecommendation(@PathParam("movie_id") Integer movieId,
                                      @HeaderParam("accept") String acceptHeader,
                                      @QueryParam("api_key") String authorizationHeader);
    @GET
    @Path("/discover/movie")
    String discoverMovies(@QueryParam("page") Integer page,
                              @QueryParam("sort_by") String sortBy,
                              @QueryParam("vote_count.gte") Integer voteCount,
                              @QueryParam("with_original_language") String withOriginalLanguage,
                              @QueryParam("with_genres") String withGenres,
                              @HeaderParam("accept") String acceptHeader,
                              @QueryParam("api_key") String authorizationHeader);

    String findMovieByTitle(@QueryParam("query") String query,
                            @HeaderParam("accept") String acceptHeader,
                            @QueryParam("api_key") String authorizationHeader);
}