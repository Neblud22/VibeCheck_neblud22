package nebel.backend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import nebel.backend.dto.EventDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String location;
    private LocalDate eventDate;
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "event_artist",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @JsonIgnore
    private List<Artist> artists;

    @Transient
    private List<String> artistNameStrings;

    @JsonSetter("artists")
    public void setArtistNameStrings(List<String> artistNameStrings) {
        this.artistNameStrings = artistNameStrings;
    }

    // Add this for ratings
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonProperty("ratings")
    @JsonManagedReference
    private List<Rating> ratings = new ArrayList<>();


//    public EventDto getDto() {
//        if (artists == null || artists.isEmpty()) {
//            return new EventDto(
//                    this.id,
//                    this.title,
//                    this.location,
//                    this.eventDate,
//                    this.imageUrl,
//                    null,
//                    null
//            );
//        }
//
//        return new EventDto(
//                this.id,
//                this.title,
//                this.location,
//                this.eventDate,
//                this.imageUrl,
//                this.artists.stream().map(this :: ).toList(),
//                this.ratings.stream().map(Rating :: getDto).toList()
//        );
//    }
}
/*
// get full names of all artists for this event
    @JsonIgnore
    public List<String> getArtistNames() {
        if (artists == null) return Collections.emptyList();

        List<String> fullNames = new ArrayList<>();
        for (Artist artist : artists) {
            fullNames.add(artist.getFirstName() + " " + artist.getLastName());
        }
        return fullNames;
    }
 */
