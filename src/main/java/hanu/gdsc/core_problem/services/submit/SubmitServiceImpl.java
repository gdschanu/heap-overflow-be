package hanu.gdsc.core_problem.services.submit;

import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.core_problem.repositories.testCase.TestCaseRepository;
import hanu.gdsc.share.error.InvalidInputError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final RunningSubmissionRepository runningSubmissionRepository;
    private final TestCaseRepository testCaseRepository;

    @Override
    public Output submit(Input input) {
        if (input.code == null || input.code.trim().isEmpty()) {
            throw new InvalidInputError("Code must be non-blank");
        }
        int testCases = testCaseRepository.count(input.problemId);
        RunningSubmission runningSubmission = RunningSubmission.create(
                input.coderId,
                input.problemId,
                input.serviceToCreate,
                input.code,
                input.programmingLanguage,
                Math.min(1, testCases),
                testCases
        );
        runningSubmissionRepository.create(runningSubmission);
        return new Output(
                runningSubmission.getId()
        );
    }
}
