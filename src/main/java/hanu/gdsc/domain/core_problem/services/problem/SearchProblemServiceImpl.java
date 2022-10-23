package hanu.gdsc.domain.core_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.core_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProblemServiceImpl implements SearchProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem getById(Id id, String serviceToCreate) throws NotFoundException {
        Problem problem = problemRepository.getById(id, serviceToCreate);
        if (problem == null) {
            throw new NotFoundException("Could not found problem");
        }
        return problem;
    }

    @Override
    public List<Problem> getByIds(List<Id> ids, String serviceToCreate) {
        return problemRepository.getByIds(ids, serviceToCreate);
    }
}
