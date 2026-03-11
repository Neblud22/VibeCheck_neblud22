package nebel.backend.controller;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.EventDto;
import nebel.backend.dto.RatingDto;
import nebel.backend.service.RatingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<RatingDto>> getEventById(@PathVariable Long eventId) {
        return ResponseEntity.ok(ratingService.getRatingsByEvent(eventId));
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<RatingDto> addRating(
            @PathVariable Long eventId,
            @RequestBody RatingDto dto) {
        ratingService.addRating(eventId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
