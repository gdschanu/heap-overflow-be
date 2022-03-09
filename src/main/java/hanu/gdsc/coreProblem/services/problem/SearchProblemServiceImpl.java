package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;

public class SearchProblemServiceImpl implements SearchProblemService{
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public List<Problem> search(Pageable pageable) {
        List<Problem> problems = problemRepository.search(pageable);
        return problems;
    }

    @Override
    public Problem getById(Id id) {
        Problem problem = problemRepository.getById(id);
        return problem;
    }

    @Override
    public long count(){
        return problemRepository.count();
    }
    
}
