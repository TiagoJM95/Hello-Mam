package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.RatingConverter;
import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateDto;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.exceptions.rating_exceptions.ListOrUserIdNotFoundException;
import com.mindera.HelloMam.exceptions.rating_exceptions.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.rating_exceptions.RatingOrMediaIdNotFoundException;
import com.mindera.HelloMam.repositories.RatingRepository;
import com.mindera.HelloMam.services.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    public List<RatingGetDto> getAllRating() {
        return ratingRepository.findAll().stream()
                .map(RatingConverter::fromRatingToRatingDto)
                .toList();
    }

    public RatingGetDto getRatingById(Integer id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(RatingNotFoundException::new);
        return RatingConverter.fromRatingToRatingDto(rating);
    }

    public List<RatingGetDto> getRatingByUserId(Integer userId) {
        List<Rating> ratings = ratingRepository.getRatingByUserId(userId);
        if(ratings.isEmpty()) {
            throw new ListOrUserIdNotFoundException();
        }
        return ratings.stream()
                .map(RatingConverter::fromRatingToRatingDto)
                .toList();
    }

    public RatingGetDto getRatingByMediaId(Integer mediaId) {
        Rating rating = ratingRepository.getRatingByMediaId(mediaId);
        if(rating == null) {
            throw new RatingOrMediaIdNotFoundException();
        }
        return RatingConverter.fromRatingToRatingDto(rating);
    }

    public RatingGetDto addNewRating(RatingCreateDto ratingCreateDto) {
        Rating ratingToAdd = RatingConverter.fromRatingDtoToRating(ratingCreateDto);
        Rating addedRating = ratingRepository.save(ratingToAdd);

        return RatingConverter.fromRatingToRatingDto(addedRating);
    }



    public RatingGetDto updateRating(Integer ratingId, RatingUpdateDto ratingUpdateDto) {

        Rating previousRating = ratingRepository.findById(ratingId).orElse(null);

        if (previousRating == null) {
            throw new RatingNotFoundException();
        }

        Rating updatedRating = RatingConverter.fromRatingUpdateDtoToRating(ratingUpdateDto);
        Rating savedRating = ratingRepository.save(updatedRating);

        return RatingConverter.fromRatingToRatingDto(savedRating);
    }
}
