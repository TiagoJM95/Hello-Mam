package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dto.MediaCreateDto;
import com.mindera.HelloMam.dto.MediaGetDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.enums.MediaType;

import java.util.List;

public interface MediaService {

    List<MediaGetDto> getAllMedia();
    MediaGetDto getMediaById(Integer id);
    List<MediaGetDto> getMediaByType(MediaType type);
    MediaGetDto getMediaByRefId(String refId);
    MediaGetDto addNewMedia(MediaCreateDto mediaCreateDto);

}