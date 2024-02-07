package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.MediaConverter;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.media.*;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.externals.clients.ExternalGameClient;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.externals.models.ExternalMovie;
import com.mindera.HelloMam.externals.clients.ExternalMovieClient;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.services.interfaces.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mindera.HelloMam.converters.MediaConverter.fromMediaEntityToMediaGetDto;
import static com.mindera.HelloMam.enums.MediaType.*;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public List<MediaGetDto> getAllMedia() {
        return mediaRepository.findAll().stream().map(MediaConverter::fromMediaEntityToMediaGetDto).toList();
    }

    @Override
    public MediaGetDto getMediaById(Long id) throws MediaNotFoundException {
        return MediaConverter.fromMediaEntityToMediaGetDto(findMediaById(id));
    }

    @Override
    public List<MediaGetDto> getMediaByType(String type) throws MediaTypeNotFoundException {
        MediaType mediaType = MediaType.getTypeByDescription(type).orElseThrow(MediaTypeNotFoundException::new);
        return mediaRepository.findByMediaType(mediaType).stream().map(MediaConverter::fromMediaEntityToMediaGetDto).toList();
    }

    @Override
    public MediaGetDto getMediaByRefId(Long refId) throws RefIdNotFoundException {
        Media media = mediaRepository.findByRefId(refId).orElseThrow(RefIdNotFoundException::new);
        return MediaConverter.fromMediaEntityToMediaGetDto(media);
    }

    @Override
    public void createMovie(ExternalMovie movie) {
        Media media = new Media();
        media.setMediaType(MOVIE);
        media.setRefId(movie.getTmdbId());
        mediaRepository.save(media);
    }

    @Override
    public void createGame(ExternalGame game) {
        Media media = new Media();
        media.setMediaType(GAME);
        media.setRefId(game.getIgdbId());
        mediaRepository.save(media);
    }

    private Media findMediaById(Long id) throws MediaNotFoundException {
        return mediaRepository.findById(id).orElseThrow(MediaNotFoundException::new);
    }

    public Media findById(Long id) throws MediaNotFoundException {
        return mediaRepository.findById(id).orElseThrow(MediaNotFoundException::new);
    }
}