package nebel.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nebel.backend.dto.ArtistDto;
import nebel.backend.pojo.Artist;
import nebel.backend.repo.ArtistRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepo artistRepo;

    public List<ArtistDto> getAllArtists() {
        return artistRepo.findAll()
                .stream()
                .map(this::mapToDtoArtist)
                .toList();
    }

    public ArtistDto getArtistById(Long id) {
        Artist artist = artistRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Artist not found"));

        return mapToDtoArtist(artist);
    }

    @Transactional // will be needed to save into repository // wenn was neues in die db weil den nicht-neuen teil speicher ma eh am ende in initdb mit .save()
    public Page<ArtistDto> getAllPagable(Pageable pageable) {
        Page<ArtistDto> artists = artistRepo.findAll(pageable).map(this::mapToDtoArtist);
        return artists;
    }

    public ArtistDto mapToDtoArtist(Artist artist) {
        ArtistDto dto = new ArtistDto();

        dto.setId(artist.getId());
        dto.setFirstName(artist.getFirstName());
        dto.setLastName(artist.getLastName());
        dto.setDescription(artist.getDescription());
        dto.setImageUrl(artist.getImageUrl());

        return dto;
    }
}
