package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.coreProblem.services.problem.RunCodeService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem extends IdentitifedDomainObject {
    private String name;
    private String description;
    private Id author;
    private long ACsCount;
    private long submissionsCount;
    private Difficulty difficulty;
    private List<TestCase> testCases;
    private List<MemoryLimit> memoryLimits;
    private List<TimeLimit> timeLimits;
    private List<ProgrammingLanguage> allowedProgrammingLanguages;

    public Problem(Id id, Long version) {
        super(id, version);
    }   

    public Problem(Id id, long version, String name, String description, Id author, int aCsCount,
            int submissionsCount, Difficulty difficulty, List<TestCase> testCases, List<MemoryLimit> memoryLimits,
            List<TimeLimit> timeLimits, List<ProgrammingLanguage> allowedProgrammingLanguages) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.author = author;
        this.ACsCount = aCsCount;
        this.submissionsCount = submissionsCount;
        this.difficulty = difficulty;
        this.testCases = testCases;
        this.memoryLimits = memoryLimits;
        this.timeLimits = timeLimits;
        this.allowedProgrammingLanguages = allowedProgrammingLanguages;
    }

    @Builder
    public static class SubmitOutput {
        public Millisecond runTime;
        public KB memory;
        public Status status;
        public TestCase failedTestCase;
    }

    public static Problem create(String name, String description, Id author, Difficulty difficulty, List<TestCase> testCases,
            List<TimeLimit> timeLimits, List<MemoryLimit> memoryLimits, List<ProgrammingLanguage> allowedProgrammingLanguages) {
        return new Problem(
            Id.generateRandom(),
            0,
            name,
            description,
            author,
            0,
            0,
            difficulty,
            testCases,
            memoryLimits,
            timeLimits,
            allowedProgrammingLanguages);
      
    }

    public SubmitOutput submit(String code, ProgrammingLanguage programmingLanguage, RunCodeService runCodeService) {
        if (!getAllowedProgrammingLanguages().contains(programmingLanguage)) {
            throw new BusinessLogicError("Bài tập không hỗ trợ ngôn ngữ lập trình " + programmingLanguage);
        }
        for (TestCase testCase : getSortedByOrdinalTestCases()) {
            RunCodeService.Output runCodeServiceOutput = runCodeService.execute(code, testCase.getInput(), programmingLanguage);
            // Check time limit
            TimeLimit timeLimit = getTimeLimitByProgrammingLanguage(programmingLanguage);
            if (runCodeServiceOutput.runTime.greaterThan(timeLimit.getTimeLimit())) {
                return SubmitOutput.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.TLE)
                        .failedTestCase(null)
                        .build();
            }
            // Check memory limit
            MemoryLimit memoryLimit = getMemoryLimitByProgrammingLanguage(programmingLanguage);
            if (runCodeServiceOutput.memory.greaterThan(memoryLimit.getMemoryLimit())) {
                return SubmitOutput.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.MLE)
                        .failedTestCase(null)
                        .build();
            }
            // Check answer
            if (runCodeServiceOutput.output.equals(testCase.getExpectedOutput())) {
                return SubmitOutput.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.WA)
                        .failedTestCase(testCase)
                        .build();
            }
        }
        return SubmitOutput.builder()
                .runTime(null) // TODO: calculate average runTime & average memory
                .memory(null)
                .status(Status.AC)
                .failedTestCase(null)
                .build();
    }

    private List<TestCase> getSortedByOrdinalTestCases() {
        List<TestCase> res = new ArrayList<>(getTestCases());
        res.sort(Comparator.comparingInt(tc -> tc.getOrdinal()));
        return res;
    }

    private MemoryLimit getMemoryLimitByProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        for (MemoryLimit memoryLimit : getMemoryLimits()) {
            if (memoryLimit.getProgrammingLanguage().equals(programmingLanguage)) {
                return memoryLimit;
            }
        }
        return null;
    }

    private TimeLimit getTimeLimitByProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        for (TimeLimit timeLimit : getTimeLimits()) {
            if (timeLimit.getProgrammingLanguage().equals(programmingLanguage)) {
                return timeLimit;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Id getAuthor() {
        return author;
    }

    public long getACsCount() {
        return ACsCount;
    }

    public long getSubmissionsCount() {
        return submissionsCount;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public List<MemoryLimit> getMemoryLimits() {
        return memoryLimits;
    }

    public List<TimeLimit> getTimeLimits() {
        return timeLimits;
    }

    public List<ProgrammingLanguage> getAllowedProgrammingLanguages() {
        return allowedProgrammingLanguages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Id author) {
        this.author = author;
    }

    public void setACsCount(int aCsCount) {
        ACsCount = aCsCount;
    }

    public void setSubmissionsCount(int submissionsCount) {
        this.submissionsCount = submissionsCount;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public void setMemoryLimits(List<MemoryLimit> memoryLimits) {
        this.memoryLimits = memoryLimits;
    }

    public void setTimeLimits(List<TimeLimit> timeLimits) {
        this.timeLimits = timeLimits;
    }

    public void setAllowedProgrammingLanguages(List<ProgrammingLanguage> allowedProgrammingLanguages) {
        this.allowedProgrammingLanguages = allowedProgrammingLanguages;
    }
}
