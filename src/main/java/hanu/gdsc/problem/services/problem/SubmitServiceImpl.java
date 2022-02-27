package hanu.gdsc.problem.services.problem;

import hanu.gdsc.problem.domains.Problem;
import hanu.gdsc.problem.domains.Submission;
import hanu.gdsc.problem.repositories.ProblemRepository;
import hanu.gdsc.problem.repositories.SubmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final ProblemRepository problemRepository;
    private final RunCodeService runCodeService;
    private final SubmissionRepository submissionRepository;

    @Override
    public Output submitForCoder(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        Problem.SubmitOutput submitOutput = problem.submitForCoder(input.code, input.programmingLanguage, runCodeService);
        Submission submission = Submission.createCoderAccessSubmission(
                problem.getId(),
                input.programmingLanguage,
                submitOutput.runTime,
                submitOutput.memory,
                input.code,
                submitOutput.status,
                submitOutput.failedTestCase
        );
        submissionRepository.save(submission);
        return Output.builder()
                .memory(submitOutput.memory)
                .runTime(submitOutput.runTime)
                .status(submitOutput.status)
                .failedTestCase(submitOutput.failedTestCase)
                .build();
    }

    @Override
    public Output submitForSystem(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        Problem.SubmitOutput submitOutput = problem.submitForSystem(input.code, input.programmingLanguage, runCodeService);
        Submission submission = Submission.createSystemAccessSubmission(
                problem.getId(),
                input.programmingLanguage,
                submitOutput.runTime,
                submitOutput.memory,
                input.code,
                submitOutput.status,
                submitOutput.failedTestCase
        );
        submissionRepository.save(submission);
        return Output.builder()
                .memory(submitOutput.memory)
                .runTime(submitOutput.runTime)
                .status(submitOutput.status)
                .failedTestCase(submitOutput.failedTestCase)
                .build();
    }
}
