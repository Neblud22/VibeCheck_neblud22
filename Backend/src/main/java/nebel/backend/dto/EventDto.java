package nebel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EventDto {
    private String title;
    private String location;
    private LocalDate eventDate;
    private String imageUrl;
    private Long artistId;
    private String artistName;
    private String artistDescription;
    private String artistImageUrl;
}
