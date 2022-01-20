package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.Millisecond;

import javax.persistence.Id;

public class TimeLimit {
    private Id id;
    private ProgrammingLanguage programmingLanguage;
    private Millisecond timeLimit;
}
