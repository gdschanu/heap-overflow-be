package hanu.gdsc.domain.contest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProblemScore {
    private int problemOrdinal;
    private double score;
    private boolean accepted;
}
