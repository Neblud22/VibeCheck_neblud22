package nebel.backend.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import nebel.backend.dto.EventDto;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String title;
    private String location;
    private LocalDate eventDate;
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "event_artists",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    public EventDto getDto() {
        return new EventDto(
                this.title,
                this.location,
                this.eventDate,
                this.imageUrl,
                this.artist.getArtistId(),
                this.artist.getFirstName() + " " + this.artist.getLastName(),
                this.artist.getDescription(),
                this.artist.getImageUrl()
        );
    }
}
