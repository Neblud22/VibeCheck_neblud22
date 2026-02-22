package nebel.backend.dto;

import lombok.Data;

@Data
public class ArtistDto {
    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;
}
