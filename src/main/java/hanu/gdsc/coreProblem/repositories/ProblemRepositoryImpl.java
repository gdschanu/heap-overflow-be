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
    public Problem getById(UUID id) {
        ProblemEntity problemEntity = problemJPARepository.getById(id);
        Problem problem = new Problem(new Id(problemEntity.getId()), problemEntity.getVersion());
        String difficultyValue = problemEntity.getDifficulty().toUpperCase();
        Difficulty difficulty = Difficulty.valueOf(difficultyValue);
        List<TimeLimit> timeLimits = new ArrayList<>();
        for(TimeLimitEntity timeLimitEntity : problemEntity.getTimeLimits()) {
            Millisecond timeLimitValue = new Millisecond(timeLimitEntity.getTimeLimit());
            String programmingLanguage = timeLimitEntity.getProgrammingLanguage().toUpperCase();
            ProgrammingLanguage programmingLanguageValue = ProgrammingLanguage.valueOf(programmingLanguage);
            TimeLimit timeLimit = new TimeLimit(programmingLanguageValue, timeLimitValue);
            timeLimits.add(timeLimit);
        }
        List<MemoryLimit> memoryLimits = new ArrayList<>();
        for(MemoryLimitEntity memoryLimitEntity : problemEntity.getMemoryLimits()) {
            KB memoryLimitValue = new KB(memoryLimitEntity.getMemoryLimit());
            String programmingLanguage = memoryLimitEntity.getProgrammingLanguage().toUpperCase();
            ProgrammingLanguage programmingLanguageValue = ProgrammingLanguage.valueOf(programmingLanguage);
            MemoryLimit memoryLimit = new MemoryLimit(programmingLanguageValue, memoryLimitValue);
            memoryLimits.add(memoryLimit);
        }
        List<TestCase> testCases = new ArrayList<>();
        for(TestCaseEntity testCaseEntity : problemEntity.getTestCases()) {
            UUID testCaseId = testCaseEntity.getId();
            long version = testCaseEntity.getVersion();
            String input = testCaseEntity.getInput();
            String expectedOutput = testCaseEntity.getExpectedOutput();
            int ordinal = testCaseEntity.getOrdinal();
            boolean isSample = testCaseEntity.isSample();
            String description = testCaseEntity.getDescription();
            TestCase testCase = new TestCase(new Id(testCaseId), version, input, expectedOutput, ordinal, isSample, description);
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
    public void save() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        
    }
}
