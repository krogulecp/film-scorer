package info.example.starter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author sebastianf
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnJava(range = ConditionalOnJava.Range.OLDER_THAN, value = JavaVersion.TWELVE)
@ConditionalOnExpression("'Hello World!'.contains('!')")
class ApplicationConfiguration {

    @Bean("mapperNumber")
    Integer testObject() {
        return 1;
    }

    @Bean
    @ConditionalOnBean(type = "info.example.rest.application.FilmController")
    @ConditionalOnClass(name = "info.example.rest.domain.Film")
    ObjectMapper objectMapper(Integer mapperNumber) {
        ObjectMapper mapper = new Jackson2ObjectMapperBuilder()
                .build();
        SimpleModule module = new SimpleModule();
        if (mapperNumber == 1) {
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
        } else {
            throw new IllegalArgumentException("mapperNumber should be equal to 1");
        }
        mapper.registerModule(module);
        return mapper;
    }
}

