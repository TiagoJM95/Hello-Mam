package com.mindera.repositories;

import com.mindera.entities.Music;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicRepository implements PanacheMongoRepository<Music> {
}