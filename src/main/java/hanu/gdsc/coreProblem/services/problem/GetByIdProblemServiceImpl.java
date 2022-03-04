package hanu.gdsc.coreProblem.services.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class GetByIdProblemServiceImpl implements GetByIdProblemService  {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem getById(Id id) {
        Problem problem = problemRepository.getById(id);
        return problem;
    }
}
