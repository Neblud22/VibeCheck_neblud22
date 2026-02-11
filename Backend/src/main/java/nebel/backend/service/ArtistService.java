package nebel.backend.service;

import lombok.RequiredArgsConstructor;
import nebel.backend.pojo.Artist;
import nebel.backend.repo.ArtistRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepo artistRepo;

    public List<Artist> getAllArtists() {
        return artistRepo.findAll();
    }

    public Artist getArtistById(Long id) {
        return artistRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Artist not found"));
    }
}
