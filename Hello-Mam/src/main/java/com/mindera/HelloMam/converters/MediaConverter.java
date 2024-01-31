package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;

import static com.mindera.HelloMam.enums.MediaType.getTypeByDescription;

public class MediaConverter {

    public static MediaGetDto fromMediaEntityToMediaGetDto(Media media) {
        return new MediaGetDto(
                media.getId(),
                media.getRefId(),
                media.getMediaType().getDescription()
        );
    }

    public static Media fromMediaCreateDtoToMediaEntity(MediaCreateDto mediaDto) throws MediaTypeNotFoundException {
        return Media.builder()
                .refId(mediaDto.refId())
                .mediaType(getTypeByDescription(mediaDto.mediaType()).orElseThrow(MediaTypeNotFoundException::new))
                .build();
    }
}