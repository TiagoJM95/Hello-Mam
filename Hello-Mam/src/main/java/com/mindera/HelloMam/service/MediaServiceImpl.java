package com.mindera.HelloMam.service;

import com.mindera.HelloMam.converter.MediaDtoConverter;
import com.mindera.HelloMam.dto.MediaDto;
import com.mindera.HelloMam.exception.MediaFoundException;
import com.mindera.HelloMam.exception.MediaNotFoundException;
import com.mindera.HelloMam.exception.RefIdNotFoundException;
import com.mindera.HelloMam.exception.TypeNotFoundException;
import com.mindera.HelloMam.model.Media;
import com.mindera.HelloMam.model.MediaType;
import com.mindera.HelloMam.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService{

    private MediaRepository mediaRepository;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    public Media getMediaById(Integer id){
        Optional<Media> media = mediaRepository.findById(id);
        if(media.isEmpty()) {
            throw new MediaNotFoundException();
        }
        return media.get();
    }
    public Media getMediaByType(MediaType type) {
        Optional<Media> media = mediaRepository.getMediaByType(type);
        if(media.isEmpty()){
            throw new TypeNotFoundException();
        }
        return media.get();
    }

    public Media getMediaByRefId(String refId) {
        /*
        se refId se trata da String com toda a informação do MediaType concreto,
        arranjar for de ser possível procurar através de uma referência parcial
        (só nome ou data ou developer/artista(...) do jogo/filme/música)
        fazer método privado abaixo deste!
         */
        if(refId == null) {
            throw new RefIdNotFoundException();
        }
        return mediaRepository.getMediaByRefId(refId);
    }

    public void addNewMedia(MediaDto mediaDto) {
        Optional<Media> optionalMedia = Optional.ofNullable(this.mediaRepository.getMediaByRefId(mediaDto.refId()));
        if(optionalMedia.isPresent()) {
            throw new MediaFoundException();
        }
        Media media = MediaDtoConverter.fromMediaDtoToMedia(mediaDto);
        mediaRepository.save(media);
    }

    public void deleteMedia(Integer mediaId) {
        boolean mediaExists = mediaRepository.existsById(mediaId);
        if(!mediaExists) {
            throw new MediaNotFoundException();
        }
        mediaRepository.deleteById(mediaId);
    }
}
