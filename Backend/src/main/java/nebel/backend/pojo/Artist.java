package nebel.backend.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import nebel.backend.dto.ArtistDto;

@Entity
@Data
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;

    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;

    public ArtistDto getDto() {
        return new ArtistDto(
                this.firstName,
                this.lastName,
                this.description,
                this.imageUrl
        );
    }
}
