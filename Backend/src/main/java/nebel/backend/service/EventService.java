package nebel.backend.service;

import lombok.RequiredArgsConstructor;
import nebel.backend.repo.EventRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepo eventRepository;


}
