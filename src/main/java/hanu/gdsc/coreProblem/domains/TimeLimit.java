package hanu.gdsc.coreProblem.domains;

public class TimeLimit {

    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    public TimeLimit(ProgrammingLanguage programmingLanguage, Millisecond timeLimit) {
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
