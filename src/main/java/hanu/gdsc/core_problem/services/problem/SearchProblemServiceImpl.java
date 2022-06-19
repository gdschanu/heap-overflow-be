package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProblemServiceImpl implements SearchProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem getById(Id id, String serviceToCreate) {
        Problem problem = problemRepository.getById(id, serviceToCreate);
        if (problem == null) {
            throw new BusinessLogicError("Could not found problem", "NOT_FOUND");
        }
        return problem;
    }

    @Override
    public List<Problem> getByIds(List<Id> ids, String serviceToCreate) {
        return problemRepository.getByIds(ids, serviceToCreate);
    }
}
