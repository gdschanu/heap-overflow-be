package hanu.gdsc.domain.practiceProblem_problem.services.testCase;

import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePracticeProblemTestCaseService {
    private final hanu.gdsc.domain.core_problem.services.testCase.CreateTestCaseService createCoreTestCaseService;
    private final ProblemRepository problemRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class InputCreate {
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    public void execute(InputCreate input) throws NotFoundException, InvalidInputException {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null)
            throw new NotFoundException("Unknown problem");
        createCoreTestCaseService.create(new hanu.gdsc.domain.core_problem.services.testCase.CreateTestCaseService.Input(
                problem.getCoreProblemId(),
                input.input,
                input.expectedOutput,
                input.ordinal,
                input.isSample,
                input.description,
                PracticeProblemProblemServiceName.serviceName
        ));
    }
}
