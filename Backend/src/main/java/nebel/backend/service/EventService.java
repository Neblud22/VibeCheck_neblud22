package nebel.backend.service;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.EventDto;
import nebel.backend.pojo.Event;
import nebel.backend.repo.EventRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepo eventRepository;

    public List<EventDto> getAllEvents() { // needs pagination later but idk
        return eventRepository.findAll()
                .stream()
                .map(Event::getDto)
                .toList();
    }

    public EventDto getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event not found"))
                .getDto();
    }

    public List<EventDto> getEventsByArtist(Long artistId) {
        return eventRepository.findByArtistArtistId(artistId)
                .stream()
                .map(Event::getDto)
                .toList();
    }
}
