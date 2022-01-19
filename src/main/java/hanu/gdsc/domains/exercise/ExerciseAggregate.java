package hanu.gdsc.domains.exercise;

import java.util.List;

public class ExerciseAggregate {
    private Exercise exercise;
    private List<MemoryLimit> memoryLimits;
    private List<TimeLimit> timeLimits;
    private List<TestCase> testCases;
}
