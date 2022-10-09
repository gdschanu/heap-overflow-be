package hanu.gdsc.core_problem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import hanu.gdsc.share.exceptions.InvalidInputException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class TimeLimit {

    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    public static final Millisecond MAX = new Millisecond(10000);

    private TimeLimit(ProgrammingLanguage programmingLanguage, Millisecond timeLimit) {
        this.programmingLanguage = programmingLanguage;
        this.timeLimit = timeLimit;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "Create", description = "Data transfer object for PracticeProblem to create" )
    public static class CreateInputTL {
        @Schema(description = "", example = "JAVA")
        public ProgrammingLanguage programmingLanguage;
        @Schema(description = "", example = "1000")
        public Millisecond timeLimit;
    }

    public static TimeLimit create(CreateInputTL input) throws InvalidInputException {
        if (input.timeLimit.greaterThan(MAX)) {
            throw new InvalidInputException("Time Limit must not be greater than 10000 millisecond.");
        }
        return new TimeLimit(
                input.programmingLanguage,
                input.timeLimit
        );
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Millisecond getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Millisecond timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLimit timeLimit = (TimeLimit) o;
        return programmingLanguage == timeLimit.programmingLanguage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmingLanguage);
    }

    @Override
    public String toString() {
        return timeLimit.toString();
    }
}
