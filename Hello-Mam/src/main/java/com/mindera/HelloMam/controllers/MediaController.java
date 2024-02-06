package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.services.implementations.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/media")
public class MediaController {

    private final MediaServiceImpl mediaService;

    @Autowired
    public MediaController(MediaServiceImpl mediaService) {
        this.mediaService = mediaService;
    }
}