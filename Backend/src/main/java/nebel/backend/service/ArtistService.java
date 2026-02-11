package nebel.backend.service;

import lombok.RequiredArgsConstructor;
import nebel.backend.repo.ArtistRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepo artistRepo;
}
