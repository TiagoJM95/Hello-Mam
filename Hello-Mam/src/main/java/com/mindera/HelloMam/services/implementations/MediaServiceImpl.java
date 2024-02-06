package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.MediaConverter;
import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.media.*;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.externals.models.ExternalMovie;
import com.mindera.HelloMam.externals.clients.ExternalMovieClient;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.services.interfaces.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mindera.HelloMam.converters.MediaConverter.fromMediaCreateDtoToMediaEntity;
import static com.mindera.HelloMam.converters.MediaConverter.fromMediaEntityToMediaGetDto;
import static com.mindera.HelloMam.enums.MediaType.MOVIE;
import static com.mindera.HelloMam.enums.MediaType.getTypeByDescription;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final ExternalMovieClient externalMovieClient;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository, ExternalMovieClient externalMovieClient) {
        this.mediaRepository = mediaRepository;
        this.externalMovieClient = externalMovieClient;
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
        return mediaRepository.findByMediaType(getTypeByDescription(type).orElseThrow(MediaTypeNotFoundException::new));
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

    public void create(String refId, MediaType mediaType) {
        Media media = new Media();
        media.setRefId(refId);
        media.setMediaType(mediaType);
        mediaRepository.save(media);
    }

    public List<ExternalMovie> getAllMovies() {
        List<ExternalMovie> movies = externalMovieClient.getAllMovies();
        for (ExternalMovie movie : movies) {
            String refId = String.valueOf(movie.getTmdbId());
            if(mediaRepository.findByRefId(refId).isEmpty()) {
                create(refId, MOVIE);
            }
        }
        return movies;
    }
}