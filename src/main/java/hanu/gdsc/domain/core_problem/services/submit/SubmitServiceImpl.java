package hanu.gdsc.domain.core_problem.services.submit;

import hanu.gdsc.domain.core_problem.models.RunningSubmission;
import hanu.gdsc.domain.core_problem.repositories.RunningSubmissionRepository;
import hanu.gdsc.domain.core_problem.repositories.TestCaseRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final RunningSubmissionRepository runningSubmissionRepository;
    private final TestCaseRepository testCaseRepository;

    @Override
    public Output submit(Input input) throws InvalidInputException {
        if (input.code == null || input.code.trim().isEmpty()) {
            throw new InvalidInputException("Code must be non-blank");
        }
        int testCases = testCaseRepository.count(input.problemId);
        RunningSubmission runningSubmission = RunningSubmission.create(
                input.coderId,
                input.problemId,
                input.serviceToCreate,
                input.code,
                input.programmingLanguage,
                0,
                testCases
        );
        runningSubmissionRepository.create(runningSubmission);
        return new Output(
                runningSubmission.getId()
        );
    }
}
