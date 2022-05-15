package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final ProblemRepository problemRepository;
    private final StartJudgeTestCaseEventQueue queue;
    private final TestCaseRepository testCaseRepository;

    public Output submit(Input input) {
        Problem problem = problemRepository.getById(input.problemId, input.serviceToCreate);
        if (problem == null) {
            throw new NotFoundError("Problem not found");
        }
        Id submissionId = Id.generateRandom();
        List<TestCase> testCases = testCaseRepository.getByProblemId(input.problemId, input.serviceToCreate);
        if (testCases.size() > 0) {
            try {
                queue.publish(new StartJudgeTestCaseEvent(
                        submissionId,
                        0,
                        problem.getId(),
                        input.code,
                        input.serviceToCreate,
                        input.programmingLanguage,
                        new Millisecond(0L),
                        new KB(0),
                        input.coderId
                ));
            } catch (Exception e) {
                e.printStackTrace();
                throw new Error("Judge Queue error");
            }
        }
        return Output.builder()
                .submissionId(submissionId)
                .build();
    }
}
