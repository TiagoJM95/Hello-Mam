package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.externals.models.ExternalMovie;

import java.util.List;

public interface MediaService {

    List<MediaGetDto> getAllMedia();
    MediaGetDto getMediaById(Long id) throws MediaNotFoundException;
    List<MediaGetDto> getMediaByType(String type) throws MediaTypeNotFoundException;
    MediaGetDto getMediaByRefId(Long refId) throws RefIdNotFoundException;
    void createMovie(ExternalMovie movie);
    void createGame(ExternalGame game);
}