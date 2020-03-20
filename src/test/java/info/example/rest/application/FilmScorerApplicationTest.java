package info.example.rest.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author krogulecp
 */
@SpringBootTest
class FilmScorerApplicationTest {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Test
    void should_load_context() {
        assertThat(applicationContext).isNotNull();
    }
}