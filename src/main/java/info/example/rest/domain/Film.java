package info.example.rest.domain;

import lombok.Builder;
import lombok.Value;

/**
 * @author krogulecp
 */
@Value
@Builder
public class Film {
    private final String title;
    private final double score;
    private final long scoresCount;
}
