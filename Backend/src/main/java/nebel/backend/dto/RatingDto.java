package nebel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class RatingDto {

    private Long ratingId;
    private Integer stars;
    private String comment;
    private LocalDateTime createdAt;
}