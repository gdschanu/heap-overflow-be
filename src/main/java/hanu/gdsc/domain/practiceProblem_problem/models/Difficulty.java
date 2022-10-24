package hanu.gdsc.domain.practiceProblem_problem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.infrastructure.practiceProblem_problem.json.DifficultyDeserializer;
import hanu.gdsc.infrastructure.practiceProblem_problem.json.DifficultySerializer;

@JsonSerialize(using = DifficultySerializer.class)
@JsonDeserialize(using = DifficultyDeserializer.class)
public enum Difficulty {
    EASY, MEDIUM, HARD;

    public static Difficulty from(String val) throws InvalidInputException {
        switch (val) {
            case "EASY":
                return EASY;
            case "MEDIUM":
                return MEDIUM;
            case "HARD":
                return HARD;
            default:
                throw new InvalidInputException(
                        "Invalid difficulty, valid " +
                                "values are: [EASY, MEDIUM, HARD].");
        }
    }
}
