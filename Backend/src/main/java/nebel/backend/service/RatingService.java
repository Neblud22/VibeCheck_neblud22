package nebel.backend.service;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.ArtistDto;
import nebel.backend.dto.RatingDto;
import nebel.backend.pojo.Artist;
import nebel.backend.pojo.Event;
import nebel.backend.pojo.Rating;
import nebel.backend.repo.EventRepo;
import nebel.backend.repo.RatingRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final RatingRepo ratingRepo;
    private final EventRepo eventRepo;

    // GET ratings of an event
    public List<RatingDto> getRatingsByEvent(Long id) {

        return ratingRepo.findByEventId(id)
                .stream()
                .map(this::mapToDtoRating)
                .toList();
    }

    // POST new rating
    public RatingDto addRating(Long id, RatingDto dto) {

        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event not found"));

        Rating rating = new Rating(
                dto.getStars(),
                dto.getComment(),
                LocalDateTime.now(),   // set automatically
                event
        );

        Rating saved = ratingRepo.save(rating);

        return mapToDtoRating(saved);
    }

    public RatingDto mapToDtoRating(Rating rating) {
        RatingDto dto = new RatingDto();

        dto.setRatingId(rating.getId());
        dto.setComment(rating.getComment());
        dto.setStars(rating.getStars());
        return dto;
    }
}