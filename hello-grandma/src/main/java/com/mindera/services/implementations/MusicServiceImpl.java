package com.mindera.services.implementations;

import com.mindera.converters.MusicConverter;
import com.mindera.dtos.MusicGetDto;
import com.mindera.entities.Music;
import com.mindera.exceptions.music.MusicNotFoundException;
import com.mindera.repositories.MusicRepository;
import com.mindera.services.interfaces.MusicService;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mindera.util.Messages.MUSIC_NOT_FOUND;
@ApplicationScoped
public class MusicServiceImpl implements MusicService {

    @Inject
    MusicRepository musicRepository;

    public List<MusicGetDto> getAllMusics(){
        return musicRepository.listAll().stream().map(MusicConverter::fromEntityToGetDto).toList();
    }

    /*public MusicGetDto create(MusicCreateDto music){
        Music musicEntity = MusicConverter.fromCreateDtoToEntity(music);
        musicRepository.persist(musicEntity);
        return MusicConverter.fromEntityToGetDto(musicEntity);
    }*/

    @CacheResult(cacheName = "musics")
    public MusicGetDto findById(String id) throws MusicNotFoundException {
        Music music = musicRepository.findByIdOptional(new ObjectId(id)).orElseThrow(()->
                new MusicNotFoundException(MUSIC_NOT_FOUND));
        return MusicConverter.fromEntityToGetDto(music);
    }
}