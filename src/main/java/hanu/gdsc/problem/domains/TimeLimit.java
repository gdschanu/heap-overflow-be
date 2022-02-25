package hanu.gdsc.problem.domains;

public class TimeLimit {

    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Millisecond getTimeLimit() {
        return timeLimit;
    }
}
