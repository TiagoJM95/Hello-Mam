package com.mindera.deprecated;

import com.mindera.deprecated.Music;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicRepository implements PanacheMongoRepository<Music> {
}