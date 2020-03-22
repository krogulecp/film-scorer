package info.example.rest.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import info.example.rest.domain.FilmRepository;
import info.example.rest.domain.FilmScorer;
import info.example.rest.domain.SimpleFilmScorer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import java.io.IOException;

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

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new Jackson2ObjectMapperBuilder()
                .build();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(FilmScoreRequest.class, new JsonDeserializer<>() {
            @Override
            public FilmScoreRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                JsonNode node = jsonParser.getCodec().readTree(jsonParser);
                int score = node.get("score").asInt();
                String title = node.get("title").asText();
                title = StringUtils.capitalize(title);
                return new FilmScoreRequest(title, score);
            }
        });
        mapper.registerModule(module);
        return mapper;
    }
}

