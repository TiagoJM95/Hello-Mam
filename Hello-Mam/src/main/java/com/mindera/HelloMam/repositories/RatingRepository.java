package com.mindera.HelloMam.repositories;

import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    /*@Query("SELECT r FROM Rating r WHERE r.id = ?1")
    RatingGetDto getRatingById(Integer id);

    @Query("SELECT r FROM Rating r WHERE r.user_id = ?1")
    List<Rating> getRatingByUserId(Integer userId);

    @Query("SELECT r FROM Rating r WHERE r.media_id = ?1")
    Rating getRatingByMediaId(Integer mediaId);*/

}
