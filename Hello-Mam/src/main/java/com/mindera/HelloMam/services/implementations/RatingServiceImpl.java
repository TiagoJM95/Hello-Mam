package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.RatingConverter;
import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateRatingDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.rating_exceptions.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.UserNotFoundException;
import com.mindera.HelloMam.repositories.RatingRepository;
import com.mindera.HelloMam.services.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mindera.HelloMam.converters.RatingConverter.*;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserServiceImpl userService;
    private final MediaServiceImpl mediaService;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, UserServiceImpl userService, MediaServiceImpl mediaService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
        this.mediaService = mediaService;
    }


    public List<RatingGetDto> getAllRating() {
        return ratingRepository.findAll().stream()
                .map(RatingConverter::fromRatingToRatingDto)
                .toList();
    }

    private Rating findById(Long id) throws RatingNotFoundException {
        return ratingRepository.findById(id).orElseThrow(RatingNotFoundException::new);
    }

    public RatingGetDto getRatingById(Long id) throws RatingNotFoundException {
        return fromRatingToRatingDto(findById(id));
    }

    public List<RatingGetDto> getRatingByUserId(Long userId) throws UserNotFoundException {
        User user = userService.findById(userId);
        return ratingRepository.findAll().stream()
                .filter(rating -> rating.getUserId().equals(user))
                .map(RatingConverter::fromRatingToRatingDto)
                .toList();
    }
    public List<RatingGetDto> getRatingByMediaId(Long mediaId) throws MediaNotFoundException {

        Media media = mediaService.findById(mediaId);

        return ratingRepository.findAll().stream()
                .filter(rating -> rating.getMediaId().equals(media))
                .map(RatingConverter::fromRatingToRatingDto)
                .toList();
    }

    public RatingGetDto addNewRating(RatingCreateDto ratingCreateDto) throws UserNotFoundException, MediaNotFoundException {
        Rating addedRating = ratingRepository.save(fromRatingDtoToRating(ratingCreateDto,
                userService.findById(ratingCreateDto.userId()),
                mediaService.findById(ratingCreateDto.mediaId())));

        return fromRatingToRatingDto(addedRating);
    }



    public RatingGetDto updateRating(Long ratingId, RatingUpdateRatingDto ratingUpdateRatingDto) throws RatingNotFoundException {
        Rating rating = findById(ratingId);
        rating.setRating(ratingUpdateRatingDto.rating());
        return fromRatingToRatingDto(rating);
    }
}