package info.example.rest.application;

import info.example.rest.domain.Film;
import info.example.rest.domain.FilmRepository;
import info.example.rest.domain.FilmScorer;
import lombok.AllArgsConstructor;

import java.util.Optional;

/**
 * @author krogulecp
 */
@AllArgsConstructor
class FilmScoreService {

    private final FilmRepository filmRepository;
    private final FilmScorer filmScorer;

    Result score(String title, double score) {
        Film filmWithUpdatedScore = filmRepository.getFilmByTitle(title)
                .map(film -> Film.builder()
                        .title(film.getTitle())
                        .score(filmScorer.score(film.getScore(), score, film.getScoresCount()))
                        .scoresCount(film.getScoresCount() + 1)
                        .build())
                .orElse(Film.builder()
                        .title(title)
                        .score(score)
                        .scoresCount(1)
                        .build());
        filmRepository.save(filmWithUpdatedScore);
        return Result.SUCCESS;
    }

    Optional<Film> getFilm(String title) {
        return filmRepository.getFilmByTitle(title);
    }
}
