package hanu.gdsc.coreProblem.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.Difficulty;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.coreProblem.repositories.JPA.ProblemJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.MemoryLimitEntity;
import hanu.gdsc.coreProblem.repositories.entities.ProblemEntity;
import hanu.gdsc.coreProblem.repositories.entities.TestCaseEntity;
import hanu.gdsc.coreProblem.repositories.entities.TimeLimitEntity;

@Repository
public class ProblemRepositoryImpl implements ProblemRepository{
    @Autowired
    private ProblemJPARepository problemJPARepository;
    
    @Override
    public Problem getById(UUID id) {
        ProblemEntity problemEntity = problemJPARepository.getById(id);
        Problem problem = new Problem(problemEntity.getId(), problemEntity.getVersion());
        problem.setName(problemEntity.getName());
        problem.setDescription(problemEntity.getDescription());
        String difficulty = problemEntity.getDifficulty().toUpperCase();
        for(Difficulty difficultyEnum : Difficulty.values()) {
            if(difficultyEnum.toString().equals(difficulty)) {
                problem.setDifficulty(difficultyEnum);
            }
        }
        for(TimeLimitEntity timeLimitEntity : problemEntity.getTimeLimits()) {
            Millisecond timelimit = new Millisecond(timeLimitEntity.getTimeLimit());
        }
        List<MemoryLimitEntity> memoryLimitsEntity = problemEntity.getMemoryLimits(); 
        List<TestCaseEntity> testCasesEntity = problemEntity.getTestCases();
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
