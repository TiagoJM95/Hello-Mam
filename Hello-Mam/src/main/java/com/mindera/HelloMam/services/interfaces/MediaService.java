package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media_exceptions.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;

import java.util.List;

public interface MediaService {

    List<MediaGetDto> getAllMedia();
    MediaGetDto getMediaById(Long id) throws MediaNotFoundException;
    List<MediaGetDto> getMediaByType(String type) throws MediaTypeNotFoundException;
    MediaGetDto getMediaByRefId(String refId) throws RefIdNotFoundException;
    MediaGetDto addNewMedia(MediaCreateDto mediaCreateDto) throws MediaTypeNotFoundException;

}