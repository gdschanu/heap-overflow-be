package hanu.gdsc.problem.domains.problem;

import java.util.List;

import hanu.gdsc.problem.domains.ID;
import hanu.gdsc.problem.domains.MemoryLimit;
import hanu.gdsc.problem.domains.ProgrammingLanguage;
import hanu.gdsc.problem.domains.TestCase;
import hanu.gdsc.problem.domains.TimeLimit;

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
