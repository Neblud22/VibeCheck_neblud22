package nebel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ArtistDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;
}
