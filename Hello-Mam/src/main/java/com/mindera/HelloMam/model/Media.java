package com.mindera.HelloMam.model;

import jakarta.persistence.*;
import lombok.*;

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
    Integer id;
    @Column(unique = true)
    String refId;
    MediaType type;

    public Media(String refId, MediaType type) {
        this.refId = refId;
        this.type = type;
    }
}
