package hanu.gdsc.core_problem.services.submit;

import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final RunningSubmissionRepository runningSubmissionRepository;

    @Override
    public Output submit(Input input) {
        RunningSubmission runningSubmission = RunningSubmission.create(
                input.coderId,
                input.problemId,
                input.serviceToCreate,
                input.code,
                input.programmingLanguage
        );
        runningSubmissionRepository.create(runningSubmission);
        return Output.builder()
                .submissionId(runningSubmission.getId())
                .build();
    }
}
