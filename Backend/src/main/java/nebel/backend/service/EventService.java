package nebel.backend.service;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.EventDto;
import nebel.backend.pojo.Event;
import nebel.backend.repo.EventRepo;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepo eventRepository;
    private final ArtistService artistService;
    private final RatingService ratingService;

//    public EventDto eventDtoMapper(Event event) {
//        EventDto dto = new EventDto();
//
//        dto.setEventDate(event.getEventDate());
//        dto.setId(event.getId());
//        dto.set
//        return dto;
//    }

    public EventDto mapToDtoEvent(Event event) {
        EventDto dto = new EventDto();

        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setLocation(event.getLocation());
        dto.setEventDate(event.getEventDate());
        dto.setImageUrl(event.getImageUrl());

        if(event.getArtists() != null){
            dto.setArtists(
                    event.getArtists()
                            .stream()
                            .map(artistService::mapToDtoArtist)
                            .toList()
            );
        }

        if(event.getRatings() != null){
            dto.setRatings(
                    event.getRatings()
                            .stream()
                            .map(ratingService::mapToDtoRating)
                            .toList()
            );
        }

        return dto;
    }

    public Page<EventDto> getAllEvents(Pageable pageable) {
//        return eventRepository.findAll()
//                .stream()
//                .map(Event::getDto)
//                .toList();
        return eventRepository.findAll(pageable)
                .map(this::mapToDtoEvent);
    }

    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event not found"));

        return mapToDtoEvent(event);
    }

    public List<EventDto> getEventsByArtist(Long artistId) {
        return eventRepository.findArtistById(artistId)
                .stream()
                .map(this::mapToDtoEvent)
                .toList();
    }
}



