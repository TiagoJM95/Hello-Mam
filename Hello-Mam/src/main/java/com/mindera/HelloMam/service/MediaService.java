package com.mindera.HelloMam.service;

import com.mindera.HelloMam.dto.MediaDto;
import com.mindera.HelloMam.model.Media;
import com.mindera.HelloMam.model.MediaType;

import java.util.List;

public interface MediaService {

    List<Media> getAllMedia();
    Media getMediaByType(MediaType type);
    Media getMediaByRefId(String refId);
    void addNewMedia(MediaDto mediaDto);
    void deleteMedia(Integer mediaId);

    //something to be discussed: void addNewMediaType(MediaType type);
    //something to be discussed: void updateMedia(Integer mediaId, Media media);
}