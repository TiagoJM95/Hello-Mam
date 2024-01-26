package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.entities.Media;

public class MediaConverter {

    public static MediaGetDto fromMediaToMediaDto(Media media) {
        return new MediaGetDto(
                media.getId(),
                media.getRefId(),
                media.getType()
        );
    }

    public static Media fromMediaDtoToMedia(MediaCreateDto mediaDto) {
        Media media = Media.builder()
                .refId(mediaDto.refId())
                .type(mediaDto.type())
                .build();
        return new Media(
                mediaDto.refId(),
                mediaDto.type()
        );
    }
}
