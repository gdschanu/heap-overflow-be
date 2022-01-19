package hanu.gdsc.domains.exercise;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TimeLimit {
    @Id
    private int id;
    private int exerciseId;
    private ProgrammingLanguage language;
    private String timeLimitInMillis;
}
