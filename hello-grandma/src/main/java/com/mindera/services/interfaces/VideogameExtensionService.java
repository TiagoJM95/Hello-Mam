package com.mindera.services.interfaces;

import com.mindera.entities.Videogame;
import com.mindera.exceptions.videogame.VideogameNotFoundException;

import java.net.MalformedURLException;

public interface VideogameExtensionService {

    Videogame getVideogameDetailsByIgdbId(String igdbId);

    Videogame create(Videogame videogame);

    Videogame findById(int id) throws MalformedURLException, VideogameNotFoundException;

    Videogame findByIgdbId(int igdbId);

    Videogame update(Videogame videogame);

    void delete(int id);
}
