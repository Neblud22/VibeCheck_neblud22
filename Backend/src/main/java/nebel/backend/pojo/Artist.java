package nebel.backend.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import nebel.backend.dto.ArtistDto;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;

    // Bidirectional ManyToMany
    @ManyToMany(mappedBy = "artists")
    private List<Event> events;

//    public ArtistDto getDto() {
//        return new ArtistDto(
//                this.id,
//                this.firstName,
//                this.lastName,
//                this.description,
//                this.imageUrl
//        );
//    }
}