package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.MediaConverter;
import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media_exceptions.*;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.services.interfaces.MediaService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Cacheable("media")
    public List<MediaGetDto> getAllMedia() {
        return mediaRepository.findAll().stream()
                .map(MediaConverter::fromMediaToMediaDto)
                .toList();
    }

    Media findById(Long id) throws MediaNotFoundException {
        return mediaRepository.findById(id).orElseThrow(MediaNotFoundException::new);
    }

    public MediaGetDto getMediaById(Long id) throws MediaNotFoundException {
        return MediaConverter.fromMediaToMediaDto(findById(id));
    }


    public List<MediaGetDto> getMediaByType(String type) throws TypeNotFoundException {
        checkIfMediaTypeExists(type);
        return mediaRepository.findByMediaType(type);
    }


    public MediaGetDto getMediaByRefId(String refId) throws RefIdNotFoundException {
        Media media = mediaRepository.findByRefId(refId).orElseThrow(RefIdNotFoundException::new);
        return MediaConverter.fromMediaToMediaDto(media);
    }


    public MediaGetDto addNewMedia(MediaCreateDto mediaCreateDto) {
        Media mediaToAdd = MediaConverter.fromMediaDtoToMedia(mediaCreateDto);
        Media addedMedia = mediaRepository.save(mediaToAdd);

        return MediaConverter.fromMediaToMediaDto(addedMedia);
    }

    private static void checkIfMediaTypeExists(String type) throws TypeNotFoundException {
        boolean exists = false;
        for(MediaType mediaType: MediaType.values()){
            if (mediaType.getDescription().equalsIgnoreCase(type)) {
                exists = true;
                break;
            }
        }
        if(!exists){
            throw new TypeNotFoundException();
        }
    }
}
