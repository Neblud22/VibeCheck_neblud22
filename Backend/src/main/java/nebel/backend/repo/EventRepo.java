package nebel.backend.repo;

import nebel.backend.pojo.Artist;
import nebel.backend.pojo.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findArtistById(Long artistId);
}
