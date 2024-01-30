package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.entities.Media;

public class MediaConverter {

    public static MediaGetDto toMediaGetDto(Media media) {
        return new MediaGetDto(
                media.getId(),
                media.getRefId(),
                media.getMediaType()
        );
    }

    public static Media toMedia(MediaCreateDto mediaDto) {
        return Media.builder()
                .refId(mediaDto.refId())
                .mediaType(mediaDto.mediaType())
                .build();
    }
}