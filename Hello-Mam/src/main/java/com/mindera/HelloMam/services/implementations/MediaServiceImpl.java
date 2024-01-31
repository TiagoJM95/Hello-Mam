package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.MediaConverter;
import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.media.*;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.services.interfaces.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mindera.HelloMam.converters.MediaConverter.fromMediaCreateDtoToMediaEntity;
import static com.mindera.HelloMam.converters.MediaConverter.fromMediaEntityToMediaGetDto;

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
                .map(MediaConverter::fromMediaEntityToMediaGetDto)
                .toList();
    }

    public Media findById(Long id) throws MediaNotFoundException {
        return mediaRepository.findById(id).orElseThrow(MediaNotFoundException::new);
    }

    public MediaGetDto getMediaById(Long id) throws MediaNotFoundException {
        return fromMediaEntityToMediaGetDto(findById(id));
    }

    public List<MediaGetDto> getMediaByType(String type) throws MediaTypeNotFoundException {
        checkIfMediaTypeExists(type);
        return mediaRepository.findByMediaType(type);
    }

    public MediaGetDto getMediaByRefId(String refId) throws RefIdNotFoundException {
        Media media = mediaRepository.findByRefId(refId).orElseThrow(RefIdNotFoundException::new);
        return fromMediaEntityToMediaGetDto(media);
    }

    public MediaGetDto addNewMedia(MediaCreateDto mediaCreateDto) throws MediaTypeNotFoundException {
        Media mediaToAdd = fromMediaCreateDtoToMediaEntity(mediaCreateDto);
        Media addedMedia = mediaRepository.save(mediaToAdd);

        return fromMediaEntityToMediaGetDto(addedMedia);
    }

    private static void checkIfMediaTypeExists(String type) throws MediaTypeNotFoundException {
        boolean exists = false;
        for(MediaType mediaType: MediaType.values()){
            if (mediaType.getDescription().equalsIgnoreCase(type)) {
                exists = true;
                break;
            }
        }
        if(!exists){
            throw new MediaTypeNotFoundException();
        }
    }
}