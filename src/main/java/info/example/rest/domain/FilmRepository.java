package info.example.rest.domain;

import java.util.Optional;

/**
 * @author krogulecp
 */
public interface FilmRepository {
    Optional<Film> getFilmByTitle(String title);

    void save(Film film);
}
