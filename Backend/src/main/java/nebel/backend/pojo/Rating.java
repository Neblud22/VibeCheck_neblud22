package nebel.backend.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nebel.backend.dto.RatingDto;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer stars;
    private String comment;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    // couldve been @Allargs
    public Rating(Integer stars, String comment, LocalDateTime createdAt, Event event) {
        this.stars = stars;
        this.comment = comment;
        this.createdAt = createdAt;
        this.event = event;
    }

//    public RatingDto getDto() {
//        return new RatingDto(
//                this.id,
//                this.stars,
//                this.comment,
//                this.createdAt
//        );
//    }
}
