package info.example.rest.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPreConfiguration {

    public static Integer counter = 0;

    @Bean("mapperNumber")
    Integer testObject() {
        return 1;
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
