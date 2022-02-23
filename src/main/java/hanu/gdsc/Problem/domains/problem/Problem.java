package hanu.gdsc.Problem.domains.problem;

import java.util.List;

import hanu.gdsc.Problem.domains.ID;
import hanu.gdsc.Problem.domains.MemoryLimit;
import hanu.gdsc.Problem.domains.ProgrammingLanguage;
import hanu.gdsc.Problem.domains.TestCase;
import hanu.gdsc.Problem.domains.TimeLimit;

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
