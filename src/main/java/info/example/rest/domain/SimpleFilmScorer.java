package info.example.rest.domain;

/**
 * @author krogulecp
 */
public class SimpleFilmScorer implements FilmScorer {

    @Override
    public double score(double actualScore, double inputScore, long scoresCount) {
        return actualScore + (inputScore - actualScore) / (scoresCount * 1.5);
    }
}
