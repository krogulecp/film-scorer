package info.example.rest.application;

import info.example.rest.domain.Film;
import info.example.rest.domain.FilmRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author krogulecp
 */
class InMemoryFilmRepository implements FilmRepository {

    private static Map<String, Film> films = new HashMap<>();

    @Override
    public Optional<Film> getFilmByTitle(final String title) {
        return Optional.ofNullable(films.get(title));
    }

    @Override
    public void save(final Film film) {
        films.put(film.getTitle(), film);
    }
}
