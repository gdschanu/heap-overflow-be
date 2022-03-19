package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.error.BusinessLogicError;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem extends IdentitifedVersioningDomainObject {
    private String name;
    private String description;
    private Id author;
    private int ACsCount;
    private int submissionsCount;
    private Difficulty difficulty;
    private List<TestCase> testCases;
    private List<MemoryLimit> memoryLimits;
    private List<TimeLimit> timeLimits;
    private List<ProgrammingLanguage> allowedProgrammingLanguages;
    private String serviceToCreate;


    public Problem(Id id, long version, String name, String description, Id author, int ACsCount, int submissionsCount, Difficulty difficulty, List<TestCase> testCases, List<MemoryLimit> memoryLimits, List<TimeLimit> timeLimits, List<ProgrammingLanguage> allowedProgrammingLanguages, String serviceToCreate) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.author = author;
        this.ACsCount = ACsCount;
        this.submissionsCount = submissionsCount;
        this.difficulty = difficulty;
        this.testCases = testCases;
        this.memoryLimits = memoryLimits;
        this.timeLimits = timeLimits;
        this.allowedProgrammingLanguages = allowedProgrammingLanguages;
        this.serviceToCreate = serviceToCreate;
    }

    public static Problem create(String name, String description, Id author, Difficulty difficulty,
                                 List<TestCase.CreateInput> createTestCaseInputs,
                                 List<MemoryLimit.CreateInput> createMemoryLimitInputs,
                                 List<TimeLimit.CreateInput> createTimeLimitInputs,
                                 List<ProgrammingLanguage> allowedProgrammingLanguages,
                                 String serviceToCreate) {
        for (MemoryLimit.CreateInput first : createMemoryLimitInputs) {
            for (MemoryLimit.CreateInput second : createMemoryLimitInputs) {
                if (first.programmingLanguage.equals(second.programmingLanguage)
                        && !first.equals(second)) {
                    throw new BusinessLogicError("Có 2 giới hạn bộ nhớ trùng nhau.", "DUPLICATE");
                }
            }
        }
        for (TimeLimit.CreateInput first : createTimeLimitInputs) {
            for (TimeLimit.CreateInput second : createTimeLimitInputs) {
                if (first.programmingLanguage.equals(second.programmingLanguage)
                        && !first.equals(second)) {
                    throw new BusinessLogicError("Có 2 giới hạn thời gian trùng nhau.", "DUPLICATE");
                }
            }
        }
        List<TestCase> testCases = new ArrayList<>();
        for (TestCase.CreateInput createTestCaseInp : createTestCaseInputs) {
            testCases.add(TestCase.create(createTestCaseInp));
        }
        List<MemoryLimit> memoryLimits = new ArrayList<>();
        for (MemoryLimit.CreateInput createMemLimitInp : createMemoryLimitInputs) {
            memoryLimits.add(MemoryLimit.create(createMemLimitInp));
        }
        List<TimeLimit> timeLimits = new ArrayList<>();
        for (TimeLimit.CreateInput createTimeLimitInp : createTimeLimitInputs) {
            timeLimits.add(TimeLimit.create(createTimeLimitInp));
        }
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
                allowedProgrammingLanguages,
                serviceToCreate
        );
    }

    public List<TestCase> getSortedByOrdinalTestCases() {
        List<TestCase> res = new ArrayList<>(getTestCases());
        res.sort(Comparator.comparingInt(tc -> tc.getOrdinal()));
        return res;
    }

    public MemoryLimit getMemoryLimitByProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        for (MemoryLimit memoryLimit : getMemoryLimits()) {
            if (memoryLimit.getProgrammingLanguage().equals(programmingLanguage)) {
                return memoryLimit;
            }
        }
        return null;
    }

    public TimeLimit getTimeLimitByProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
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
