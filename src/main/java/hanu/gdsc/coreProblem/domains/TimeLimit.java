package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import lombok.Builder;

public class TimeLimit extends IdentifiedDomainObject {

    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    public TimeLimit(Id id, ProgrammingLanguage programmingLanguage, Millisecond timeLimit) {
        super(id);
        this.programmingLanguage = programmingLanguage;
        this.timeLimit = timeLimit;
    }

    @Builder
    public static class CreateInput {
        public ProgrammingLanguage programmingLanguage;
        public Millisecond timeLimit;
    }

    public static TimeLimit create(CreateInput input) {
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
