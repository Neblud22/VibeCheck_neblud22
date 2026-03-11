package nebel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nebel.backend.pojo.Artist;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventDto {
    private Long id;
    private String title;
    private String location;
    private LocalDate eventDate;
    private String imageUrl;
    private List<ArtistDto> artists;
    private List<RatingDto> ratings;
}
