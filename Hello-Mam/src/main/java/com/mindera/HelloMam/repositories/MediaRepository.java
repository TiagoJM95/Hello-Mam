package com.mindera.HelloMam.repositories;

import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT m FROM Media m WHERE m.type = ?1")
    List<Media> getMediaByType(MediaType type);
    Media getMediaByRefId(String refId);

}
