package com.mindera.HelloMam.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "media_id", nullable = false)
    //@JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media mediaId;

    private Float rating;
}