package com.mindera.HelloMam.entities;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "\"user\"")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String name;

    private LocalDate dateOfBirth;

    private List<String> interests;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "userId")
    private List<Rating> ratings;

    private boolean active;
}