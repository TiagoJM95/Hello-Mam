package com.mindera.HelloMam.entities;

import com.mindera.HelloMam.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;


@Entity
//@Data
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String name;

    private LocalDate dateOfBirth;
/*
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "user"
    )*/

    private List<MediaType> interests;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "user"
    )
    private List<Rating> ratings;

    private boolean active;



}
