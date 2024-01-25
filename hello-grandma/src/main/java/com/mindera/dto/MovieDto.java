package com.mindera.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
//Falta quarkus

public class MovieDto {

    private String title;

    private String director;

    private String genre;

    private String rating;


}


