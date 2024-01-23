package com.mindera.HelloMam.converter;

import com.mindera.HelloMam.dto.MediaDto;
import com.mindera.HelloMam.model.Media;

public class MediaDtoConverter {

    public static MediaDto fromMediaToMediaDto(Media media) {
        return new MediaDto(
                media.getRefId(),
                media.getType()
        );
    }

    public static Media fromMediaDtoToMedia(MediaDto mediaDto) {
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
