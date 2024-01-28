package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media_exceptions.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.media_exceptions.TypeNotFoundException;

import java.util.List;

public interface MediaService {

    List<MediaGetDto> getAllMedia();
    MediaGetDto getMediaById(Long id) throws MediaNotFoundException;
    List<MediaGetDto> getMediaByType(MediaType type) throws TypeNotFoundException;
    MediaGetDto getMediaByRefId(String refId) throws RefIdNotFoundException;
    MediaGetDto addNewMedia(MediaCreateDto mediaCreateDto);

}