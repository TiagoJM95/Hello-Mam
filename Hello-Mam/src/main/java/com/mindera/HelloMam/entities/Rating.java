package com.mindera.HelloMam.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media mediaId;
    private Float rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(userId, rating.userId) && Objects.equals(mediaId, rating.mediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, mediaId);
    }
}