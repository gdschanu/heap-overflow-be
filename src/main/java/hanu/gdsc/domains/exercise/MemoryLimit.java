package hanu.gdsc.domains.exercise;

public class MemoryLimit {
    private int id;
    private int exerciseId;
    private ProgrammingLanguage language;
    private int memoryLimitInKB;

    public int getId() {
        return id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public int getMemoryLimitInKB() {
        return memoryLimitInKB;
    }
}
