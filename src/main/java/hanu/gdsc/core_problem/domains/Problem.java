package hanu.gdsc.core_problem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.error.DuplicatedError;
import hanu.gdsc.share.error.InvalidInputError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem extends IdentitifedVersioningDomainObject {
    private String name;
    private String description;
    private Id author;
    private List<MemoryLimit> memoryLimits;
    private List<TimeLimit> timeLimits;
    private List<ProgrammingLanguage> allowedProgrammingLanguages;
    private String serviceToCreate;

    private Problem(Id id, long version, String name, String description, Id author, List<MemoryLimit> memoryLimits, List<TimeLimit> timeLimits, List<ProgrammingLanguage> allowedProgrammingLanguages, String serviceToCreate) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.author = author;
        this.memoryLimits = memoryLimits;
        this.timeLimits = timeLimits;
        this.allowedProgrammingLanguages = allowedProgrammingLanguages;
        this.serviceToCreate = serviceToCreate;
    }

    public static Problem create(String name,
                                 String description,
                                 Id author,
                                 List<MemoryLimit.CreateInput> createMemoryLimitInputs,
                                 List<TimeLimit.CreateInput> createTimeLimitInputs,
                                 List<ProgrammingLanguage> allowedProgrammingLanguages,
                                 String serviceToCreate) {
        Problem problem = new Problem(
                Id.generateRandom(),
                0,
                name,
                description,
                author,
                new ArrayList<>(),
                new ArrayList<>(),
                allowedProgrammingLanguages,
                serviceToCreate
        );
        for (MemoryLimit.CreateInput mem : createMemoryLimitInputs) {
            problem.addMemoryLimit(MemoryLimit.create(mem));
        }
        for (TimeLimit.CreateInput input : createTimeLimitInputs) {
            problem.addTimeLimit(TimeLimit.create(input));
        }
        return problem;
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

    public List<MemoryLimit> getMemoryLimits() {
        return Collections.unmodifiableList(memoryLimits);
    }

    public List<TimeLimit> getTimeLimits() {
        return Collections.unmodifiableList(timeLimits);
    }

    public List<ProgrammingLanguage> getAllowedProgrammingLanguages() {
        return allowedProgrammingLanguages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMemoryLimit(MemoryLimit memoryLimit) {
        if (memoryLimit == null)
            throw new InvalidInputError("memoryLimit must be not null");
        for (MemoryLimit existed : memoryLimits)
            if (existed.equals(memoryLimit))
                throw new DuplicatedError("Duplicated memory limit");
        memoryLimits.add(memoryLimit);
    }

    public void addTimeLimit(TimeLimit timeLimit) {
        if (timeLimit == null)
            throw new InvalidInputError("timeLimit must be not null");
        for (TimeLimit existed : timeLimits)
            if (existed.equals(timeLimit))
                throw new DuplicatedError("Duplicated time limit");
        timeLimits.add(timeLimit);
    }

    public void clearMemoryLimits() {
        memoryLimits.clear();
    }

    public void clearTimeLimits() {
        timeLimits.clear();
    }

    public void setAllowedProgrammingLanguages(List<ProgrammingLanguage> allowedProgrammingLanguages) {
        this.allowedProgrammingLanguages = allowedProgrammingLanguages;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}
