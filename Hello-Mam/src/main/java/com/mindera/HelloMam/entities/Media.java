package com.mindera.HelloMam.entities;

import com.mindera.HelloMam.enums.MediaType;
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
    @Column
    Integer id;
    @Column
    String refId;
    @Column
    MediaType type;

    public Media(String refId, MediaType type) {
        this.refId = refId;
        this.type = type;
    }
}
