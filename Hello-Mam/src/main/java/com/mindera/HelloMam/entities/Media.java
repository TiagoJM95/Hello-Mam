package com.mindera.HelloMam.entities;

import com.mindera.HelloMam.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long refId;
    private MediaType mediaType;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "mediaId")
    private List<Rating> ratings;

}