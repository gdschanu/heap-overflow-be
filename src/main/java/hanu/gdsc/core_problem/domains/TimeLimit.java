package hanu.gdsc.core_problem.domains;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import hanu.gdsc.share.error.InvalidInputError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class TimeLimit extends IdentifiedDomainObject {

    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    private TimeLimit(Id id, ProgrammingLanguage programmingLanguage, Millisecond timeLimit) {
        super(id);
        this.programmingLanguage = programmingLanguage;
        this.timeLimit = timeLimit;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateInput {
        public ProgrammingLanguage programmingLanguage;
        public Millisecond timeLimit;
    }

    public static TimeLimit create(CreateInput input) {
        if (input.timeLimit.greaterThan(2000)) {
            throw new InvalidInputError("Time Limit must not be greater than 2000 millisecond.");
        }
        return new TimeLimit(
                Id.generateRandom(),
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
}
