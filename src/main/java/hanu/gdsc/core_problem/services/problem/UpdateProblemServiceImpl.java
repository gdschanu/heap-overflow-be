package hanu.gdsc.core_problem.services.problem;

import java.util.List;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class UpdateProblemServiceImpl implements UpdateProblemService{
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public void update(Input input) {
        Problem problem = problemRepository.getById(input.id, input.serviceToCreate);
        if(problem == null) {
            throw new BusinessLogicError("this problem doesn't exist", "NOT_FOUND");
        }
        List<MemoryLimit> memoryLimits = updateMemoryLimit(problem.getMemoryLimits(), input.memoryLimits);
        List<TimeLimit> timeLimits = updateTimeLimit(problem.getTimeLimits(), input.timeLimits);
        if(input.name != null) {
            problem.setName(input.name);
        }
        if(input.description != null){
            problem.setDescription(input.description);
        }
        if(input.memoryLimits != null){
            problem.setMemoryLimits(memoryLimits);
        }
        if(input.timeLimits != null){
            problem.setTimeLimits(timeLimits);
        }
        if(input.allowedProgrammingLanguages != null){
            problem.setAllowedProgrammingLanguages(input.allowedProgrammingLanguages);
        } 
        problemRepository.update(problem);
    }
    
    private static List<MemoryLimit> updateMemoryLimit(List<MemoryLimit> memoryLimits, List<UpdateMemoryLimitInput> updateMemoryLimitInputs) {
        for(int i = 0; i < memoryLimits.size(); i++) {
            if(updateMemoryLimitInputs.get(i) != null) {
                if(updateMemoryLimitInputs.get(i).memoryLimit != null) {
                    memoryLimits.get(i).setMemoryLimit(updateMemoryLimitInputs.get(i).memoryLimit);
                }
                if(updateMemoryLimitInputs.get(i).programmingLanguage != null) {
                    memoryLimits.get(i).setProgrammingLanguage(updateMemoryLimitInputs.get(i).programmingLanguage);
                }
            } else {
                break;
            }
        }
        return memoryLimits;
    }

    private static List<TimeLimit> updateTimeLimit(List<TimeLimit> timeLimits, List<UpdateTimeLimitInput> updateTimeLimitInputs) {
        for(int i = 0; i < timeLimits.size(); i++) {
            if(updateTimeLimitInputs.get(i) != null) {
                if(updateTimeLimitInputs.get(i).timeLimit != null) {
                    timeLimits.get(i).setTimeLimit(updateTimeLimitInputs.get(i).timeLimit);
                }
                if(updateTimeLimitInputs.get(i).programmingLanguage != null) {
                    timeLimits.get(i).setProgrammingLanguage(updateTimeLimitInputs.get(i).programmingLanguage);
                }
            } else {
                break;
            }
        }
        return timeLimits;
    }
}

