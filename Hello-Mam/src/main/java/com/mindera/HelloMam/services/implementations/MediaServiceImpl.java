package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.MediaConverter;
import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media_exceptions.*;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.services.interfaces.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<MediaGetDto> getAllMedia() {
        return mediaRepository.findAll().stream()
                .map(MediaConverter::fromMediaToMediaDto)
                .toList();
    }


    public MediaGetDto getMediaById(Integer id) throws MediaNotFoundException {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);

        return MediaConverter.fromMediaToMediaDto(media);
    }


    public List<MediaGetDto> getMediaByType(MediaType type) {
        List<Media> medias = mediaRepository.getMediaByType(type);
        if(medias.isEmpty()){
            throw new TypeNotFoundException();
        }
        return medias.stream()
                .map(MediaConverter::fromMediaToMediaDto)
                .collect(Collectors.toList());
    }


    public MediaGetDto getMediaByRefId(String refId) {
        if(refId == null) {
            throw new RefIdNotFoundException();
        }
        Media media = mediaRepository.getMediaByRefId(refId);
        return MediaConverter.fromMediaToMediaDto(media);
    }


    public MediaGetDto addNewMedia(MediaCreateDto mediaCreateDto) {
        Media mediaToAdd = MediaConverter.fromMediaDtoToMedia(mediaCreateDto);
        Media addedMedia = mediaRepository.save(mediaToAdd);

        return MediaConverter.fromMediaToMediaDto(addedMedia);
    }
}
