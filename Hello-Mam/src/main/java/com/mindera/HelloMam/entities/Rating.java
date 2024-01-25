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
    @Column
    private Integer id;

    @Column
    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @Column
    @ManyToOne
    @MapsId
    @JoinColumn(name = "media_id", nullable = false)
    //@JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media mediaId;

    @Column
    private Float rating;

    public Rating(User userId, Media mediaId, Float rating) {
        this.userId = userId;
        this.mediaId = mediaId;
        this.rating = rating;
    }
}
