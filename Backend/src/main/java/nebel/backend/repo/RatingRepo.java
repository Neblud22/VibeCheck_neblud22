package nebel.backend.repo;

import nebel.backend.pojo.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating, Long> {
    List<Rating> findByEventId(Long eventId);
}
