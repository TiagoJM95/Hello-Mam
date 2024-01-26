package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;

import java.util.List;

public interface MediaService {

    List<MediaGetDto> getAllMedia();
    MediaGetDto getMediaById(Integer id) throws MediaNotFoundException;
    List<MediaGetDto> getMediaByType(MediaType type);
    MediaGetDto getMediaByRefId(String refId);
    MediaGetDto addNewMedia(MediaCreateDto mediaCreateDto);

}