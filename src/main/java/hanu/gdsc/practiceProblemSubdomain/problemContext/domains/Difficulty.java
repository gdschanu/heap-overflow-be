package hanu.gdsc.practiceProblemSubdomain.problemContext.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.practiceProblemSubdomain.problemContext.json.DifficultyDeserializer;
import hanu.gdsc.practiceProblemSubdomain.problemContext.json.DifficultySerializer;
import hanu.gdsc.share.error.BusinessLogicError;

@JsonSerialize(using = DifficultySerializer.class)
@JsonDeserialize(using = DifficultyDeserializer.class)
public enum Difficulty {
    EASY, MEDIUM, HARD;

    public static Difficulty from(String val) {
        switch (val) {
            case "EASY":
                return EASY;
            case "MEDIUM":
                return MEDIUM;
            case "HARD":
                return HARD;
            default:
                throw new BusinessLogicError(
                        "Invalid difficulty, valid " +
                                "values are: [EASY, MEDIUM, HARD].",
                        "INVALID_DIFFICULTY");
        }
    }
}
