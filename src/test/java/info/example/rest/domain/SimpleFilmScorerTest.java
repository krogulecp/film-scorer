package info.example.rest.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author krogulecp
 */
class SimpleFilmScorerTest {

    private FilmScorer filmScorer;

    @BeforeEach
    void setUp() {
        filmScorer = new SimpleFilmScorer();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10, 7, 10, 9.8",
            "7, 10, 10, 7.2",
            "10, 5, 112, 9.970238095238095"
    })
    void should_calculate_correct_score(double actualScore, double inputScore, long scoresCount, double expectedResult) {

        //when
        double calculatedScore = filmScorer.score(actualScore, inputScore, scoresCount);

        //then
        assertThat(calculatedScore).isEqualTo(expectedResult);
    }
}