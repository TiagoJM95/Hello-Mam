package com.mindera.converters;

import com.mindera.deprecated.create.MusicCreateDto;
import com.mindera.dtos.MusicGetDto;
import com.mindera.entities.Music;

public class MusicConverter {

        public static Music fromCreateDtoToEntity(MusicCreateDto musicCreateDto){
            return Music.builder()
                    .build();
        }

        public static MusicGetDto fromEntityToGetDto(Music music){
            return new MusicGetDto();
        }
}