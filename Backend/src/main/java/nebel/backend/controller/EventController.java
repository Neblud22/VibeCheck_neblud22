package nebel.backend.controller;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.EventDto;
import nebel.backend.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    public EventDto getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping("/artist/{artistId}")
    public List<EventDto> getEventsByArtist(@PathVariable Long artistId) {
        return eventService.getEventsByArtist(artistId);
    }
}
