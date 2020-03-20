package info.example.rest.application;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author krogulecp
 */
@RestController
@AllArgsConstructor
class FilmController {

    private final FilmScoreService filmScoreService;

    @PostMapping(path = "/score")
    public ResponseEntity<FilmScoreResponse> scoreFilm(@RequestBody @Validated FilmScoreRequest filmScoreRequest) {
        Result result = filmScoreService.score(filmScoreRequest.getTitle(), filmScoreRequest.getScore());
        return ResponseEntity.ok(new FilmScoreResponse(result));
    }

    @GetMapping(path = "/film/{title}")
    public ResponseEntity<FilmResponse> getFilmScore(@PathVariable String title) {
        return ResponseEntity.of(filmScoreService.getFilm(title)
                .map(film -> new FilmResponse(film.getTitle(), film.getScore())));
    }
}
