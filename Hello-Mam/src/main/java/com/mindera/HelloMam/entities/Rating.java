package com.mindera.HelloMam.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rating")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    //@JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    //@JoinColumn(name = "media_id", nullable = false)
    private Media mediaId;

    private Float rating;
}