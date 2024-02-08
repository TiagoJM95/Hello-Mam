package com.mindera.HelloMam.externals.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Schema(name = "ExternalGame", description = "ExternalGame Entity Information")
public class ExternalGame implements Serializable {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ExternalGame Id", example = "123")
    private Long igdbId;
    @Schema(description = "ExternalGame's title", example = "Elden Ring")
    private String name;
    @Schema(description = "ExternalGame's release date", example = "2024-02-09")
    private String releaseDate;
    @Schema(description = "ExternalGame's rating", example = "9.5")
    private Double rating;
    @JsonProperty("genreIds")
    @Schema(name = "genres", description = "ExternalGame's genres", example = "[\"Adventure\", \"RPG\"]")
    private List<String> genres;
}