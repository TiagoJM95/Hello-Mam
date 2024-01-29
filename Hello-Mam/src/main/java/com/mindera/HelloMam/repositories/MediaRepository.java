package com.mindera.HelloMam.repositories;

import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    List<MediaGetDto> findByMediaType(String mediaType);
    Optional<Media> findByRefId(String refId);
}