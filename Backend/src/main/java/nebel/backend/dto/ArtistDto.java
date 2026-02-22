package nebel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtistDto {
    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;
}
