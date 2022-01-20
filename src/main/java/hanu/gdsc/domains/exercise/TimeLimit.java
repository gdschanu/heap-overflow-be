package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.Millisecond;

import javax.persistence.Id;

public class TimeLimit {
    private Id id;
    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;

    public Id getId() {
        return id;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Millisecond getTimeLimit() {
        return timeLimit;
    }
}
