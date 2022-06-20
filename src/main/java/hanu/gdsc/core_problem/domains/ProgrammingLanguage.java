package hanu.gdsc.core_problem.domains;

import hanu.gdsc.share.error.BusinessLogicError;
import hanu.gdsc.share.error.InvalidInputError;

public enum ProgrammingLanguage {

    JAVA, PYTHON, CPLUSPLUS, JAVASCRIPT;


    public static ProgrammingLanguage from(String val) {
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
                throw new InvalidInputError(
                        "Invalid programming language, valid " +
                                "values are: [JAVA, PYTHON, CPLUSPLUS, JAVASCRIPT].");
        }
    }
}
