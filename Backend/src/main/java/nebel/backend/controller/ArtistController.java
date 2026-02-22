package nebel.backend.controller;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.ArtistDto;
import nebel.backend.pojo.Artist;
import nebel.backend.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable Long artistId) {
        return ResponseEntity.ok(artistService.getArtistById(artistId));
    }
}
