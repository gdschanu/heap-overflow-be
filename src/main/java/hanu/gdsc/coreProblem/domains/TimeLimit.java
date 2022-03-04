package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

public class TimeLimit extends IdentitifedDomainObject{

    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    public TimeLimit(Id id, long version, ProgrammingLanguage programmingLanguage, Millisecond timeLimit) {
        super(id, version);
        this.programmingLanguage = programmingLanguage;
        this.timeLimit = timeLimit;
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
