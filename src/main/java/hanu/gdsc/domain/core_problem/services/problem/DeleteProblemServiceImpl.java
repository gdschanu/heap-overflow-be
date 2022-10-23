package hanu.gdsc.domain.core_problem.services.problem;

import hanu.gdsc.domain.core_problem.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.domain.share.models.Id;

import java.util.List;

@Service
public class DeleteProblemServiceImpl implements DeleteProblemService{
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public void deleteById(Id id) {
        problemRepository.deleteById(id);
    }

    @Override
    public void deleteMany(List<Id> ids) {
        problemRepository.deleteByIds(ids);
    }
}
