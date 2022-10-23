package hanu.gdsc.domain.core_problem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.infrastructure.practiceProblem_problem.json.ProgrammingLanguageDeserializer;
import hanu.gdsc.infrastructure.practiceProblem_problem.json.ProgrammingLanguageSerializer;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

@JsonSerialize(using = ProgrammingLanguageSerializer.class)
@JsonDeserialize(using = ProgrammingLanguageDeserializer.class)
public enum ProgrammingLanguage {

    JAVA, PYTHON, CPLUSPLUS, JAVASCRIPT;


    public static ProgrammingLanguage from(String val) throws InvalidInputException {
        switch (val) {
            case "JAVA":
                return JAVA;
            case "PYTHON":
                return PYTHON;
            case "CPLUSPLUS":
                return CPLUSPLUS;
            case "JAVASCRIPT":
                return JAVASCRIPT;
            default:
                throw new InvalidInputException(
                        "Invalid programming language, valid " +
                                "values are: [JAVA, PYTHON, CPLUSPLUS, JAVASCRIPT].");
        }
    }
}
