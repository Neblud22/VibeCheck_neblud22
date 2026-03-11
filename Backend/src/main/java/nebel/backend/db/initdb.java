package nebel.backend.db;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import nebel.backend.pojo.Artist;
import nebel.backend.pojo.Event;
import nebel.backend.repo.ArtistRepo;
import nebel.backend.repo.EventRepo;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class initdb {

    private final ArtistRepo artistRepository;
    private final EventRepo eventRepository;


    @PostConstruct
    public void initDatabase() {
        try {

            InputStream artistStream = this.getClass().getResourceAsStream("/vibes_artists.json");
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            List<Artist> artists = objectMapper.readerForListOf(Artist.class).readValue(artistStream);

            artistRepository.saveAll(artists);

            InputStream eventStream = this.getClass().getResourceAsStream("/vibes_events.json");
            List<Event> events = objectMapper.readerForListOf(Event.class).readValue(eventStream);

            for (Event event : events) {
                System.out.println("EVENT: " + event.getTitle());
                System.out.println("JSON artistNames: " + event.getArtistNameStrings());
            }
            for (Event event : events) {
                List<Artist> realArtists = new ArrayList<>();

                if (event.getArtistNameStrings() != null) {
                    for (String fullName : event.getArtistNameStrings()) {
                        String[] parts = fullName.split(" ", 2);
                        String firstName = parts[0];
                        String lastName = parts.length > 1 ? parts[1] : "";
                        log.info(firstName + lastName);
                        artistRepository
                                .findFirstByFirstNameAndLastName(firstName, lastName)
                                .ifPresent(realArtists::add);
                    }
                }
                event.setArtists(realArtists);

                if (event.getRatings() != null) {
                    event.getRatings().forEach(rating -> rating.setEvent(event));
                }
            }

            eventRepository.saveAll(events);

        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Initialisieren der Datenbank", e);
        }
    }

    /* private class EventJson {
        public String title;
        public String location;
        public String date;
        public String imageUrl;
        public List<String> artist;
    } */
}