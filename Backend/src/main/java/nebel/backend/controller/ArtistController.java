package nebel.backend.controller;

import nebel.backend.service.ArtistService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

}
