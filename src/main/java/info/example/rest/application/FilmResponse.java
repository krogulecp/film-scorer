package info.example.rest.application;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @author krogulecp
 */
@AllArgsConstructor
@NoArgsConstructor
class FilmResponse {
    private String title;
    private String score;

    public FilmResponse(String title, double score) {
        this.title = title;
        this.score = parseScore(score);
    }

    public String getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = parseScore(score);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String parseScore(double score) {
        return ScoreParser.parseScore(score);
    }

    private static class ScoreParser {

        private static final char DECIMAL_SEPARATOR = '.';
        private static final char GROUPING_SEPARATOR = ' ';
        private static final String DECIMAL_FORMAT_PATTERN = "####0.00";

        private static final DecimalFormat decimalFormat;

        static {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator(DECIMAL_SEPARATOR);
            otherSymbols.setGroupingSeparator(GROUPING_SEPARATOR);
            decimalFormat = new DecimalFormat(DECIMAL_FORMAT_PATTERN, otherSymbols);
            decimalFormat.setRoundingMode(RoundingMode.UP);
        }

        private static String parseScore(double score) {
            return decimalFormat.format(score);
        }
    }
}
