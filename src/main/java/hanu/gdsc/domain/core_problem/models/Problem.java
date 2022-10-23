package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentitifedVersioningDomainObject;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;

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
                                 List<MemoryLimit.CreateInputML> createMemoryLimitInputs,
                                 List<TimeLimit.CreateInputTL> createTimeLimitInputs,
                                 List<ProgrammingLanguage> allowedProgrammingLanguages,
                                 String serviceToCreate) throws InvalidInputException {
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
        final List<TimeLimit> timeLimits = new ArrayList<>();
        for (TimeLimit.CreateInputTL inputTL : createTimeLimitInputs)
            timeLimits.add(TimeLimit.create(inputTL));
        problem.setTimeLimits(timeLimits);
        problem.setMemoryLimits(createMemoryLimitInputs.stream()
                .map(i -> MemoryLimit.create(i))
                .collect(Collectors.toList()));
        for (ProgrammingLanguage programmingLanguage : allowedProgrammingLanguages) {
            if (problem.getMemoryLimitByProgrammingLanguage(programmingLanguage) == null) {
                throw new InvalidInputException("Programming language " + programmingLanguage + " doesn't have memory limit.");
            }
            if (problem.getTimeLimitByProgrammingLanguage(programmingLanguage) == null) {
                throw new InvalidInputException("Programming language " + programmingLanguage + " doesn't have time limit.");
            }
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

    public void setTimeLimits(List<TimeLimit> timeLimits) throws InvalidInputException {
        Set<ProgrammingLanguage> existed = new HashSet<>();
        for (TimeLimit timeLimit : timeLimits) {
            if (existed.contains(timeLimit.getProgrammingLanguage())) {
                throw new InvalidInputException("Contains duplicate time limit");
            }
            existed.add(timeLimit.getProgrammingLanguage());
        }
        this.timeLimits = timeLimits;
    }

    public void setMemoryLimits(List<MemoryLimit> memoryLimits) throws InvalidInputException {
        Set<ProgrammingLanguage> existed = new HashSet<>();
        for (MemoryLimit memoryLimit : memoryLimits) {
            if (existed.contains(memoryLimit.getProgrammingLanguage())) {
                throw new InvalidInputException("Contains duplicate memory limit");
            }
            existed.add(memoryLimit.getProgrammingLanguage());
        }
        this.memoryLimits = memoryLimits;
    }

    public void setAllowedProgrammingLanguages(List<ProgrammingLanguage> allowedProgrammingLanguages) {
        this.allowedProgrammingLanguages = new HashSet<>(allowedProgrammingLanguages)
                .stream()
                .collect(Collectors.toList());
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}
