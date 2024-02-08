package com.mindera.controllers;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class GameControllerTest {

    int testPort = Integer.parseInt(System.getProperty("quarkus.http.test-port", "8080"));
    @Test
    void getAllGames() {
        given()
                .port(testPort)
                .when().get("/api/v1/games")
                .then()
                .statusCode(200);
    }

    @Test
    void getGameById() throws IOException {
        String jsonPath = "src/test/java/com/mindera/json/getGameByIdJSON.json";
        String jsonBody = new String(Files.readAllBytes(Paths.get(jsonPath)));

        String httpResponse = given()
                .pathParam("id", 194662)
                .port(testPort)
                .when().get("/api/v1/games/{id}")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(httpResponse).isEqualToIgnoringWhitespace(jsonBody);
    }

    @Test
    void getGameById404NotFound() {
        given()
                .pathParam("id", 123456)
                .port(testPort)
                .when().get("/api/v1/games/{id}")
                .then()
                .statusCode(404);
    }


    @Test
    void getGamesByTitle() {
        given()
                .pathParam("title", "The Witcher")
                .port(testPort)
                .when().get("/api/v1/games/title/{title}")
                .then()
                .statusCode(200);
    }

    @Test
    void getGameRecommendations() {
        given()
                .pathParam("id", 194662)
                .port(testPort)
                .when().get("/api/v1/games/recommendation/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    void getGamesByGenre() {
        given()
                .pathParam("genre", "RPG")
                .port(testPort)
                .when().get("/api/v1/games/genres/{genre}")
                .then()
                .statusCode(200);
    }

    @Test
    void getTopRatedGames() {
        given()
                .port(testPort)
                .when().get("/api/v1/games/top")
                .then()
                .statusCode(200);
    }
}
