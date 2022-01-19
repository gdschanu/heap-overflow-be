package hanu.gdsc.domains.exercise;

public class TimeLimit {
    private int id;
    private int exerciseId;
    private ProgrammingLanguage language;
    private long timeLimitInMillis;

    public int getId() {
        return id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public long getTimeLimitInMillis() {
        return timeLimitInMillis;
    }
}
