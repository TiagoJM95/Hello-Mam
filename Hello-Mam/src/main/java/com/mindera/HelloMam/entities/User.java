package com.mindera.HelloMam.entities;

import com.mindera.HelloMam.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "\"user\"")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String name;
    private LocalDate dateOfBirth;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "userId")
    private List<Rating> ratings;
    private boolean active;
}