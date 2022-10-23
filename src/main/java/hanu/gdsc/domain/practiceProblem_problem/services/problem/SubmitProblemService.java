package hanu.gdsc.domain.practiceProblem_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.services.submit.SubmitService;
import hanu.gdsc.domain.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.domain.practiceProblem_problem.domains.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmitProblemService {
    private ProblemRepository problemRepository;

    private SubmitService submitCoreProblemService;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id coderId;
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }


    public SubmitService.Output submit(Input input) throws NotFoundException, InvalidInputException {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new NotFoundException("Problem not found");
        }
        return submitCoreProblemService.submit(new SubmitService.Input(
                input.coderId,
                problem.getCoreProblemProblemId(),
                ServiceName.serviceName,
                input.code,
                input.programmingLanguage
        ));
    }
}
