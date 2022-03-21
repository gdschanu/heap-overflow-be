package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SearchProblemServiceImpl implements SearchProblemService{
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem getById(Id id) {
        Problem problem = problemRepository.getById(id);   
        if(problem == null) {
            throw new BusinessLogicError("Không tìm thấy bài toán", "NOT_FOUND");
        }
        return problem;
    }

    @Override
    public long count(){
        return problemRepository.count();
    }

    @Override
    public List<Problem> getByIds(List<Id> ids) {
        return null;
    }

}
