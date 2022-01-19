package hanu.gdsc.domains.exercise;

import java.util.List;

public class ExerciseAggregate {
    private Exercise exercise;
    private MemoryLimits memoryLimits;
    private TimeLimits timeLimits;
    private TestCases testCases;
    private AllowedProgrammingLanguages allowedProgrammingLanguages;

    public void checkAllowedProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        for (AllowedProgrammingLanguage allowedProgrammingLanguage : allowedProgrammingLanguages.getData()) {
            if (allowedProgrammingLanguage.equals(programmingLanguage)) {
                return;
            }
        }
        throw new Error("Programming language " + programmingLanguage + " is not allowed for this exercise.");

    }

    public void checkLimits(ProgrammingLanguage language, long runTimeInMillis, int memoryConsumedInKB) {
        if (runTimeInMillis > timeLimits.getMemoryLimitByLanguage(language).getTimeLimitInMillis())
            throw new Error("Time limit exceeds");
        if (memoryConsumedInKB > memoryLimits.getMemoryLimitByLanguage(language).getMemoryLimitInKB()) {
            throw new Error("Memory limit exceeds");
        }
    }

    public void checkAllTestCases(List<String> actualOutputs) {
        testCases.sortTestCasesByOrdinal();
        for (int i = 0; i < actualOutputs.size(); i++) {
            String actualOutput = actualOutputs.get(i);
            TestCase tc = testCases.getData().get(i);
            if (!tc.getOutput().equals(actualOutput)) {
                throw new Error("Wrong answer at test case " + tc.getOrdinal());
            }
        }
    }


    public Exercise getExercise() {
        return exercise;
    }

    public MemoryLimits getMemoryLimits() {
        return memoryLimits;
    }

    public TimeLimits getTimeLimits() {
        return timeLimits;
    }

    public TestCases getTestCases() {
        return testCases;
    }

    public AllowedProgrammingLanguages getAllowedProgrammingLanguages() {
        return allowedProgrammingLanguages;
    }
}
