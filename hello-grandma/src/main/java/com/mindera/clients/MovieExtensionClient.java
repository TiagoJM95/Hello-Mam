package com.mindera.clients;

import com.mindera.entities.Movie;
import com.mindera.entities.MovieExtension;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/3")
@RegisterRestClient(configKey="tmdb-api")
public interface MovieExtensionClient {
    @GET
    @Path("/movie/{movie_id}")
    Movie getMovieDetailsByTmdbId(
            @PathParam("movie_id") String movieId,
            @HeaderParam("accept") String acceptHeader,
            @QueryParam("api_key") String authorizationHeader);

    @GET
    @Path("/movie/{movie_id}/recommendations")
    MovieExtension getMovieRecommendationByTmdbId(
            @PathParam("movie_id") Integer movieId,
            @HeaderParam("accept") String acceptHeader,
            @QueryParam("api_key") String authorizationHeader);

    @GET
    @Path("/discover/movie")
    MovieExtension discoverMoviesWithFilters(
            @QueryParam("page") Integer page,
            @QueryParam("sort_by") String sortBy,
            @QueryParam("vote_count.gte") Integer voteCount,
            @QueryParam("with_original_language") String withOriginalLanguage,
            @QueryParam("with_genres") String withGenres,
            @HeaderParam("accept") String acceptHeader,
            @QueryParam("api_key") String authorizationHeader);

    @GET
    @Path("/search/movie")
    MovieExtension findMovieByTitle(
            @QueryParam("query") String query,
            @QueryParam("language") String language,
            @QueryParam("page") Integer page,
            @HeaderParam("accept") String acceptHeader,
            @QueryParam("api_key") String authorizationHeader);

    @GET
    @Path("/discover/movie")
    MovieExtension getTop5(
            @QueryParam("page") Integer page,
            @QueryParam("sort_by") String sortBy,
            @QueryParam("vote_count.gte") Integer voteCount,
            @QueryParam("with_original_language") String withOriginalLanguage,
            @HeaderParam("accept") String acceptHeader,
            @QueryParam("api_key") String authorizationHeader);
}