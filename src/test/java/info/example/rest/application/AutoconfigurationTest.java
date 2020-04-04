package info.example.rest.application;

import info.example.starter.ApplicationPreConfiguration;
import info.example.starter.ApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutoconfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(info.example.rest.application.ApplicationConfiguration.class))
            .withConfiguration(AutoConfigurations.of(ApplicationConfiguration.class))
            .withConfiguration(AutoConfigurations.of(ApplicationPreConfiguration.class));

    @Test
    @Profile("default")
    void should_find_bean_in_context() {
        this.contextRunner.run((context -> {
            assertThat(context).hasBean("mapperNumber");
        }));
    }
}
