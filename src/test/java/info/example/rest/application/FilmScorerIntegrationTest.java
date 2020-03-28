package info.example.rest.application;

import info.example.rest.starter.FilmScoreRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author krogulecp
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmScorerIntegrationTest {

    @Autowired
    ApplicationContext context;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void should_score_film_and_get_score() {

        ResponseEntity<FilmScoreResponse> filmScoreResponse = testRestTemplate
                .postForEntity("/score", new FilmScoreRequest("Rambo", 10), FilmScoreResponse.class);

        assertThat(filmScoreResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<FilmResponse> film = testRestTemplate.getForEntity("/film/Rambo", FilmResponse.class);

        assertThat(film.getStatusCode()).isEqualTo(HttpStatus.OK);

        FilmResponse filmResponseEntity = film.getBody();

        assertThat(filmResponseEntity).isNotNull();
        assertThat(filmResponseEntity.getTitle()).isEqualTo("Rambo");
        assertThat(filmResponseEntity.getScore()).isEqualTo("10.00");
    }

    @Test
    void should_score_2_times_and_get_score() {

        //given
        testRestTemplate.postForEntity("/score", new FilmScoreRequest("Rambo", 10), FilmScoreResponse.class);
        testRestTemplate.postForEntity("/score", new FilmScoreRequest("Rambo", 9), FilmScoreResponse.class);

        //when
        ResponseEntity<FilmResponse> film = testRestTemplate.getForEntity("/film/Rambo", FilmResponse.class);

        //then
        assertThat(film.getStatusCode()).isEqualTo(HttpStatus.OK);

        FilmResponse filmResponseEntity = film.getBody();

        assertThat(filmResponseEntity).isNotNull();
        assertThat(filmResponseEntity.getTitle()).isEqualTo("Rambo");
        assertThat(filmResponseEntity.getScore()).isEqualTo("9.67");
    }

    @Test
    void should_save_film_with_first_uppercase() {

        //given
        testRestTemplate.postForEntity("/score", new FilmScoreRequest("the Terminator", 5), FilmScoreResponse.class);

        //when
        ResponseEntity<FilmResponse> film = testRestTemplate.getForEntity("/film/The Terminator", FilmResponse.class);

        //then
        assertThat(film.getStatusCode()).isEqualTo(HttpStatus.OK);

        FilmResponse filmResponseEntity = film.getBody();

        assertThat(filmResponseEntity).isNotNull();
        assertThat(filmResponseEntity.getTitle()).isEqualTo("The Terminator");
    }

    @Test
    void should_fail_searching_film_using_lowercase() {

        //given
        testRestTemplate.postForEntity("/score", new FilmScoreRequest("the Terminator", 5), FilmScoreResponse.class);

        //when
        ResponseEntity<FilmResponse> film = testRestTemplate.getForEntity("/film/the Terminator", FilmResponse.class);

        //then
        assertThat(film.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void should_success_when_bean_exist_in_context() {

        //given
        //when
        Object test = context.getBean("mapperNumber");

        //then
        assertThat(test).isNotNull();
        assertThat(test).isInstanceOf(Integer.class);
        assertThat((Integer) test).isEqualTo(1);
    }

}
