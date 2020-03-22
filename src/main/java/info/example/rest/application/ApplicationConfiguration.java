package info.example.rest.application;

import info.example.rest.domain.FilmRepository;
import info.example.rest.domain.FilmScorer;
import info.example.rest.domain.SimpleFilmScorer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author krogulecp
 */
@Configuration
class ApplicationConfiguration {

    @Bean
    FilmScoreService filmScoreService(FilmRepository filmRepository, FilmScorer filmScorer) {
        return new FilmScoreService(filmRepository, filmScorer);
    }

    @Bean
    FilmRepository filmRepository() {
        return new InMemoryFilmRepository();
    }

    @Bean
    FilmScorer filmScorer() {
        return new SimpleFilmScorer();
    }
}

