package hanu.gdsc.coreProblem.services.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class DeleteProblemServiceImpl implements DeleteProblemService{
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public void execute(Id id) {
        problemRepository.deleteById(id);
    }
}
