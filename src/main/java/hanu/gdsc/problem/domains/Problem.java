package hanu.gdsc.problem.domains;

import hanu.gdsc.problem.services.problem.RunCodeService;
import hanu.gdsc.share.domains.ID;
import hanu.gdsc.share.domains.IdentitifedDomainObject;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem extends IdentitifedDomainObject {
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
    public Accessibility accessibility;

    public Problem(ID id, long version) {
        super(id, version);
    }

    @Builder
    public static class SubmitOutput {
        public Millisecond runTime;
        public KB memory;
        public Status status;
        public TestCase failedTestCase;
    }

    public SubmitOutput submitForCoder(String code, ProgrammingLanguage programmingLanguage, RunCodeService runCodeService) {
        if (getAccessibility() != Accessibility.CODER) {
            throw new BusinessLogicError("Bạn không có quyền truy cập vào bài tập này.");
        }
        return submit(code, programmingLanguage, runCodeService);
    }

    public SubmitOutput submitForSystem(String code, ProgrammingLanguage programmingLanguage, RunCodeService runCodeService) {
        if (getAccessibility() != Accessibility.SYSTEM) {
            throw new BusinessLogicError("Bạn không có quyền truy cập vào bài tập này.");
        }
        return submit(code, programmingLanguage, runCodeService);
    }

    private SubmitOutput submit(String code, ProgrammingLanguage programmingLanguage, RunCodeService runCodeService) {
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

    public String getAuthor() {
        return author;
    }

    public int getACsCount() {
        return ACsCount;
    }

    public int getSubmissionsCount() {
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

    public Accessibility getAccessibility() {
        return accessibility;
    }
}
