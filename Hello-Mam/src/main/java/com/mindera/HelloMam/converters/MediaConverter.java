package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.entities.Media;

public class MediaConverter {

    public static MediaGetDto fromMediaEntityToMediaGetDto(Media media) {
        return new MediaGetDto(
                media.getId(),
                media.getRefId(),
                media.getMediaType().getDescription()
        );
    }

   /* public static Media fromMediaCreateDtoToMediaEntity(MediaCreateDto mediaDto) throws MediaTypeNotFoundException {
        return Media.builder()
                .refId(mediaDto.refId())
                .mediaType(getTypeByDescription(mediaDto.mediaType()).orElseThrow(MediaTypeNotFoundException::new))
                .build();
    }*/
}