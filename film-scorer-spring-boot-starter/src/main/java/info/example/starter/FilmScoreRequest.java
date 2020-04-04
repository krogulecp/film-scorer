package info.example.starter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author krogulecp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmScoreRequest {

    @NotEmpty
    private String title;

    @Min(1)
    @Max(10)
    private double score;
}
