package com.mindera.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
class MovieControllerTest {

    int testPort = Integer.parseInt(System.getProperty("quarkus.http.test-port", "8080"));


    @Test
    void getAllMovies() {

        given()
                .port(testPort)
                .when().get("/api/v1/movies")
                .then()
                .statusCode(200);
    }

    @Test
    void getMovieById() throws IOException {
        String jsonPath = "src/test/java/com/mindera/json/getMovieByIdJSON.json";
        String jsonBody = new String(Files.readAllBytes(Paths.get(jsonPath)));

        String httpResponse = given()
                .pathParam("id", 769L)
                .port(testPort)
                .when().get("/api/v1/movies/{id}")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(httpResponse).isEqualToIgnoringWhitespace(jsonBody);
    }

    @Test
    void getMovieById404NotFound() {
        given()
                .pathParam("id", 1234L)
                .port(testPort)
                .when().get("/api/v1/movies/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    void getMovieByTitle() {
        given()
                .pathParam("title", "Harry Potter and the Deathly Hallows - Part 2")
                .port(testPort)
                .when().get("/api/v1/movies/title/{title}")
                .then()
                .statusCode(200);
    }


    @Test
    void getMovieRecommendation() {
        given()
                .pathParam("id", 769L)
                .port(testPort)
                .when().get("/api/v1/movies/recommendation/{id}")
                .then()
                .statusCode(200);
    }


    @Test
    void discoverMovies() {
        given()
                .pathParam("genre", "Action")
                .port(testPort)
                .when().get("/api/v1/movies/genres/{genre}")
                .then()
                .statusCode(200);
    }


    @Test
    void getTopRatedMovies() {
        given()
                .port(testPort)
                .when().get("/api/v1/movies/top")
                .then()
                .statusCode(200);
    }

}