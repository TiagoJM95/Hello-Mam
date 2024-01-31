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
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media mediaId;
    private Float rating;
}