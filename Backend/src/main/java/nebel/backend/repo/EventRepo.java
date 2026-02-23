package nebel.backend.repo;

import nebel.backend.pojo.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByArtistArtistId(Long artistId);
}
