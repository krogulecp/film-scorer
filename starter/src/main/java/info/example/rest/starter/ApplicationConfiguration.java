package info.example.rest.starter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Configuration
public class ApplicationConfiguration {

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
