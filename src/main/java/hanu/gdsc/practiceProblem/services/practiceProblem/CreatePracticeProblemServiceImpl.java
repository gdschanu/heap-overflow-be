package hanu.gdsc.practiceProblem.services.practiceProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.CreateProblemService;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class CreatePracticeProblemServiceImpl implements CreatePracticeProblemService {
    @Autowired
    private CreateProblemService createProblemService;
    @Autowired
    private PracticeProblemRepository practiceProblemRepository;

    @Override
    public Id create(Input input) {
        Id coreProblemId = createProblemService.execute(input.inputProblem);
        Problem practiceProblem = Problem.create(
            coreProblemId,
            input.nameCategory
        );
        practiceProblemRepository.create(practiceProblem);
        return practiceProblem.getId();
    }
}
