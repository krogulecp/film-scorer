package info.example.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SomeProperties.class)
public class ApplicationPreConfiguration {

    public static Integer counter = 0;

    @Bean("mapperNumber")
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
