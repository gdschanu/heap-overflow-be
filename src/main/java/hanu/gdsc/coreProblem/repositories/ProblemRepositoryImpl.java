package hanu.gdsc.coreProblem.repositories;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.coreProblem.repositories.JPA.ProblemJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.*;
import hanu.gdsc.share.domains.Id;

@Repository
public class ProblemRepositoryImpl implements ProblemRepository{
    @Autowired
    private ProblemJPARepository problemJPARepository;
    
    @Override
    public Problem getById(Id id) {
        ProblemEntity problemEntity = problemJPARepository.getById(id.getValue());
        Problem problem = new Problem(new Id(problemEntity.getId()), problemEntity.getVersion());
        String difficultyValue = problemEntity.getDifficulty().toUpperCase();
        Difficulty difficulty = Difficulty.valueOf(difficultyValue);
        List<TimeLimit> timeLimits = new ArrayList<>();
        for(TimeLimitEntity timeLimitEntity : problemEntity.getTimeLimits()) {
            UUID timeLimitId = timeLimitEntity.getId();
            long timeLimitVersion = timeLimitEntity.getVersion();
            Millisecond timeLimitValue = new Millisecond(timeLimitEntity.getTimeLimit());
            String programmingLanguage = timeLimitEntity.getProgrammingLanguage().toUpperCase();
            ProgrammingLanguage programmingLanguageValue = ProgrammingLanguage.valueOf(programmingLanguage);
            TimeLimit timeLimit = new TimeLimit(new Id(timeLimitId), timeLimitVersion, programmingLanguageValue, timeLimitValue);
            timeLimits.add(timeLimit);
        }
        List<MemoryLimit> memoryLimits = new ArrayList<>();
        for(MemoryLimitEntity memoryLimitEntity : problemEntity.getMemoryLimits()) {
            UUID memoryLimitId = memoryLimitEntity.getId();
            long memoryLimitVersion = memoryLimitEntity.getVersion();
            KB memoryLimitValue = new KB(memoryLimitEntity.getMemoryLimit());
            String programmingLanguage = memoryLimitEntity.getProgrammingLanguage().toUpperCase();
            ProgrammingLanguage programmingLanguageValue = ProgrammingLanguage.valueOf(programmingLanguage);
            MemoryLimit memoryLimit = new MemoryLimit(new Id(memoryLimitId), memoryLimitVersion, programmingLanguageValue, memoryLimitValue);
            memoryLimits.add(memoryLimit);
        }
        List<TestCase> testCases = new ArrayList<>();
        for(TestCaseEntity testCaseEntity : problemEntity.getTestCases()) {
            UUID testCaseId = testCaseEntity.getId();
            long testCaseVersion = testCaseEntity.getVersion();
            String input = testCaseEntity.getInput();
            String expectedOutput = testCaseEntity.getExpectedOutput();
            int ordinal = testCaseEntity.getOrdinal();
            boolean isSample = testCaseEntity.isSample();
            String description = testCaseEntity.getDescription();
            TestCase testCase = new TestCase(new Id(testCaseId), testCaseVersion, input, expectedOutput, ordinal, isSample, description);
            testCases.add(testCase);
        }
        List<ProgrammingLanguage> allowedProgrammingLanguages = new ArrayList<>();
        for(String allowedProgrammingLanguageEntity : problemEntity.getAllowedProgrammingLanguages()) {
            ProgrammingLanguage allowedProgrammingLanguage = ProgrammingLanguage.valueOf(allowedProgrammingLanguageEntity);
            allowedProgrammingLanguages.add(allowedProgrammingLanguage);
        }
        problem.setName(problemEntity.getName());
        problem.setDescription(problemEntity.getDescription());
        problem.setDifficulty(difficulty);
        problem.setTimeLimits(timeLimits);
        problem.setMemoryLimits(memoryLimits);
        problem.setTestCases(testCases);
        problem.setAllowedProgrammingLanguages(allowedProgrammingLanguages);
        return problem;
    }

    @Override
    public void create(Problem problem) {
        List<TestCaseEntity> testCasesEntity = new ArrayList<>();
        List<MemoryLimitEntity> memoryLimitsEntity = new ArrayList<>();
        List<TimeLimitEntity> timeLimitsEntity = new ArrayList<>();
        List<String> allowedProgrammingLanguagesEntity = new ArrayList<>();
        for(TestCase testCase : problem.getTestCases()) {
            testCasesEntity.add(
                TestCaseEntity.builder()
                .id(testCase.getId().getValue())
                .input(testCase.getInput())
                .expectedOutput(testCase.getExpectedOutput())
                .ordinal(testCase.getOrdinal())
                .isSample(testCase.isSample())
                .description(testCase.getDescription())
                .build()
            );
        }
        for(MemoryLimit memoryLimit : problem.getMemoryLimits()) {
            memoryLimitsEntity.add(
                MemoryLimitEntity.builder()
                    .id(memoryLimit.getId().getValue())
                    .programmingLanguage(memoryLimit.getProgrammingLanguage().toString())
                    .memoryLimit(memoryLimit.getMemoryLimit().getValue())
                    .build()
            );
        }
        for(TimeLimit timeLimit : problem.getTimeLimits()) {
            timeLimitsEntity.add(
                TimeLimitEntity.builder()
                    .id(timeLimit.getId().getValue())
                    .programmingLanguage(timeLimit.getProgrammingLanguage().toString())
                    .timeLimit(timeLimit.getTimeLimit().getValue())
                    .build()
            );
        }
        for(ProgrammingLanguage allowedProgrammingLanguage : problem.getAllowedProgrammingLanguages()) {
            allowedProgrammingLanguagesEntity.add(
                allowedProgrammingLanguage.toString()
            );
        }
        problemJPARepository.save(
            ProblemEntity.builder()
            .id(problem.getId().getValue())
            .name(problem.getName())
            .description(problem.getDescription())
            .testCases(testCasesEntity)
            .timeLimits(timeLimitsEntity)
            .memoryLimits(memoryLimitsEntity)
            .allowedProgrammingLanguages(allowedProgrammingLanguagesEntity)
            .build()
        );    
    }

    @Override
    public void deleteById(Id id) {
        // TODO Auto-generated method stub
        
    }
}
