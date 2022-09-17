package hanu.gdsc.core_problem.services.submit;

import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.share.error.InvalidInputError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final RunningSubmissionRepository runningSubmissionRepository;

    @Override
    public Output submit(Input input) {
        if (input.code == null || input.code.trim().isEmpty()) {
            throw new InvalidInputError("Code must be non-blank");
        }
        RunningSubmission runningSubmission = RunningSubmission.create(
                input.coderId,
                input.problemId,
                input.serviceToCreate,
                input.code,
                input.programmingLanguage
        );
        runningSubmissionRepository.create(runningSubmission);
        return new Output(
                runningSubmission.getId()
        );
    }
}
