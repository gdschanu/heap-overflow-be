package hanu.gdsc.problemService.domains.problem;

import java.util.List;

import hanu.gdsc.problemService.domains.ID;
import hanu.gdsc.problemService.domains.MemoryLimit;
import hanu.gdsc.problemService.domains.ProgrammingLanguage;
import hanu.gdsc.problemService.domains.TestCase;
import hanu.gdsc.problemService.domains.TimeLimit;

public class Problem {

    private ID id;
    private String name;
    private String description;
    private String author;
    private int ACsCount;
    private int submissionsCount;
    private Difficulty difficulty;
    public List<TestCase> testCases;
    public List<MemoryLimit> memoryLimits;
    public List<TimeLimit> timeLimits;
    public List<ProgrammingLanguage> allowedProgrammingLanguages;

}
