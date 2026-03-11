package nebel.backend.controller;

import lombok.RequiredArgsConstructor;
import nebel.backend.dto.ArtistDto;
import nebel.backend.service.ArtistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/api/artists_pagable") // /artists_pagable?page=0&size=3&sortBy=firstName&sortDirection=desc
    public ResponseEntity<Page<ArtistDto>> getAllPagable(
        @RequestParam int page, // welche seite
        @RequestParam int size, // wieviel pro
        @RequestParam String sortBy,
        @RequestParam String sortDirection
    ) {
        Sort sort = sortDirection.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ArtistDto> artists = artistService.getAllPagable(pageable);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable Long artistId) {
        return ResponseEntity.ok(artistService.getArtistById(artistId));
    }
}
