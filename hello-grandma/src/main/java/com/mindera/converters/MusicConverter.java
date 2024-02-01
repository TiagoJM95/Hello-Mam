package com.mindera.converters;

import com.mindera.dtos.MusicGetDto;
import com.mindera.entities.Music;

public class MusicConverter {
        public static MusicGetDto fromEntityToGetDto(Music music){
            return new MusicGetDto();
        }
}