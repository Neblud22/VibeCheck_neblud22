package nebel.backend.service;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.EventDto;
import nebel.backend.pojo.Event;
import nebel.backend.repo.EventRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepo eventRepository;

    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(Event::getDto)
                .toList();
    }
}
