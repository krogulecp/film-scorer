package info.example.rest.domain;

/**
 * @author krogulecp
 */
public interface FilmScorer {
    double score(double actualScore, double inputScore, long scoresCount);
}
