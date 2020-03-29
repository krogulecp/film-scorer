package info.example.rest.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(SomeProperties.class)
public class ApplicationPreConfiguration {

    public static Integer counter = 0;

    @Bean("mapperNumber")
    @Profile("test")
    Integer testObject(SomeProperties properties) {
        return properties.getMapper();
    }

    @Bean("testA")
    Integer testAObject() {
        return counter++;
    }

    @Bean("testB")
    Integer testBObject() {
        return counter++;
    }
}
