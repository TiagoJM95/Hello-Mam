package com.mindera.HelloMam.repository;

import com.mindera.HelloMam.model.Media;
import com.mindera.HelloMam.model.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {

    @Query("SELECT m FROM Media m WHERE m.type = ?1")
    Optional<Media> getMediaByType(MediaType type);
    Media getMediaByRefId(String refId);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE media AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
