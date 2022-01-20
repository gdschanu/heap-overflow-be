package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ID;
import hanu.gdsc.domains.KB;
import hanu.gdsc.domains.Millisecond;
import hanu.gdsc.domains.endUser.EndUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise {
    private ID id;
    private String name;
    private String description;
    private EndUser author;
    private int ACsCount;
    private int submissionsCount;
    private Difficulty difficulty;
    public List<TestCase> testCases;
    public List<MemoryLimit> memoryLimits;
    public List<TimeLimit> timeLimits;
    public List<ProgrammingLanguage> allowedProgrammingLanguages;

    public void checkAllowedProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        if (!allowedProgrammingLanguages.contains(programmingLanguage))
            throw new Error("Programming language " + programmingLanguage + " is not allowed for this exercise.");
    }

    public void checkAllTestCases(List<String> actualOutputs) {
        for (int i = 0; i < actualOutputs.size(); i++) {
            if (!actualOutputs.get(i).equals(testCases.get(i).getExpectedOutput())) {
                throw new Error("Wrong answer at testcase " + testCases.get(i).getOrdinal());
            }
        }
    }

    public void checkTimeAndMemoryLimit(ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memoryConsumed) {
        if (memoryConsumed.greaterThan(getMemoryLimitByProgrammingLanguage(programmingLanguage).getMemoryLimit())) {
            throw new Error("Memory limit exceeded");
        }
        if (runTime.greaterThan(getTimeLimitByProgrammingLanguage(programmingLanguage).getTimeLimit())) {
            throw new Error("Time limit exceeded");
        }
    }

    public MemoryLimit getMemoryLimitByProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        for (MemoryLimit memoryLimit : memoryLimits)
            if (memoryLimit.getProgrammingLanguage().equals(programmingLanguage))
                return memoryLimit;
        throw new Error("Cannot find memory limit for this programming language: " + programmingLanguage);
    }

    public TimeLimit getTimeLimitByProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        for (TimeLimit timeLimit : timeLimits)
            if (timeLimit.getProgrammingLanguage().equals(programmingLanguage))
                return timeLimit;
        throw new Error("Cannot find time limit for this programming language: " + programmingLanguage);
    }

    public List<String> getSortedTestCasesInputs() {
        sortTestCasesByOrdinal();
        List<String> result = new ArrayList<>();
        for (TestCase tc : testCases) {
            result.add(tc.getInput());
        }
        return result;
    }

    public void sortTestCasesByOrdinal() {
        testCases.sort(Comparator.comparingInt(x -> x.getOrdinal()));
    }
}
