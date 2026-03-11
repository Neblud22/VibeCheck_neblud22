package nebel.backend.repo;

import nebel.backend.pojo.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepo extends JpaRepository<Artist, Long> {

    Optional<Artist> findFirstByFirstNameAndLastName(String firstName, String lastName);
    //@Query("SELECT a.id FROM Artist a WHERE a.lastName = :lastName AND a.firstName = :firstName")
    //Long getArtistByFirstNameAndLastName(
    //   @Param("firstName") String firstName,
    //   @Param("lastName") String lastName
    //);
}
