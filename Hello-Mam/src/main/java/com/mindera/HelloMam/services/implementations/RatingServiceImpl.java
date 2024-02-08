package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.RatingConverter;
import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateRatingDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.rating.DuplicateRatingException;
import com.mindera.HelloMam.exceptions.rating.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.user.UserNotFoundException;
import com.mindera.HelloMam.repositories.RatingRepository;
import com.mindera.HelloMam.services.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("ratings")
    public List<RatingGetDto> getAllRating() {
        return ratingRepository.findAll().stream()
                .map(RatingConverter::fromRatingEntityToRatingGetDto)
                .toList();
    }

    private Rating findById(Long id) throws RatingNotFoundException {
        return ratingRepository.findById(id).orElseThrow(RatingNotFoundException::new);
    }

    @Cacheable("ratings")
    public RatingGetDto getRatingById(Long id) throws RatingNotFoundException {
        return fromRatingEntityToRatingGetDto(findById(id));
    }

    @Cacheable("ratings")
    public List<RatingGetDto> getRatingByUserId(Long userId) throws UserNotFoundException {
        User user = userService.findById(userId);
        return ratingRepository.findAll().stream()
                .filter(rating -> rating.getUserId().equals(user))
                .map(RatingConverter::fromRatingEntityToRatingGetDto)
                .toList();
    }

    @Cacheable("ratings")
    public List<RatingGetDto> getRatingByMediaId(Long mediaId) throws MediaNotFoundException {

        Media media = mediaService.findById(mediaId);

        return ratingRepository.findAll().stream()
                .filter(rating -> rating.getMediaId().equals(media))
                .map(RatingConverter::fromRatingEntityToRatingGetDto)
                .toList();
    }

    @CacheEvict(value = "ratings", allEntries = true)
    public RatingGetDto addNewRating(RatingCreateDto ratingCreateDto) throws UserNotFoundException, MediaNotFoundException, DuplicateRatingException {
        Rating ratingToAdd = fromRatingCreateDtoToRatingEntity(ratingCreateDto,
                userService.findById(ratingCreateDto.userId()),
                mediaService.findById(ratingCreateDto.mediaId()));

        if(checkIfRatingExists(ratingToAdd)){
            throw new DuplicateRatingException("Rating already exists!");
        }
        ratingRepository.save(ratingToAdd);
        return fromRatingEntityToRatingGetDto(ratingToAdd);
    }

    @CacheEvict(value = "ratings", allEntries = true)
    public RatingGetDto updateRating(Long ratingId, RatingUpdateRatingDto ratingUpdateRatingDto) throws RatingNotFoundException {
        Rating rating = findById(ratingId);
        rating.setRating(ratingUpdateRatingDto.rating());
        ratingRepository.save(rating);
        return fromRatingEntityToRatingGetDto(rating);
    }

    private boolean checkIfRatingExists(Rating ratingToAdd) {
        for (Rating rating : ratingRepository.findAll()) {
            if (rating.equals(ratingToAdd)) {
                return true;
            }
        }
        return false;
    }
}