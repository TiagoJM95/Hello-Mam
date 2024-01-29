package com.mindera.HelloMam.entities;

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
    @Column
    private Long id;
    private String refId;
    private String mediaType;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "mediaId")
    private List<Rating> ratings;
}