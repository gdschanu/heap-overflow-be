package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import hanu.gdsc.share.error.InvalidInputError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class TimeLimit extends IdentifiedDomainObject {

    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    private TimeLimit(Id id, ProgrammingLanguage programmingLanguage, Millisecond timeLimit) {
        super(id);
        this.programmingLanguage = programmingLanguage;
        this.timeLimit = timeLimit;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateInput {
        public ProgrammingLanguage programmingLanguage;
        public Millisecond timeLimit;
    }

    public static TimeLimit create(CreateInput input) {
        if (input.timeLimit.greaterThan(5000)) {
            throw new InvalidInputError("Time Limit must not be greater than 5000 millisecond.");
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

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setTimeLimit(Millisecond timeLimit) {
        this.timeLimit = timeLimit;
    }
}
