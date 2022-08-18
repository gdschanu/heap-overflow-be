package hanu.gdsc.core_problem.services.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchRunningSubmissionServiceImpl implements SearchRunningSubmissionService {
    private final RunningSubmissionRepository runningSubmissionRepository;
    private final ObjectMapper objectMapper;

    public SearchRunningSubmissionServiceImpl(RunningSubmissionRepository runningSubmissionRepository,
                                              ObjectMapper objectMapper) throws IOException {
        this.runningSubmissionRepository = runningSubmissionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Output getByIdAndCoderId(Id id, Id coderId, String serviceToCreate) {
        RunningSubmission runningSubmission = runningSubmissionRepository.getByIdAndCoderId(id, coderId, serviceToCreate);
        return toOutput(runningSubmission);
    }

    @Override
    public List<Output> getByCoderId(int page, int perPage, Id coderId, String serviceToCreate) {
        return runningSubmissionRepository.getByCoderId(page, perPage, coderId, serviceToCreate)
                .stream()
                .map(item -> toOutput(item))
                .collect(Collectors.toList());
    }

    @Override
    public Output getById(Id id, String serviceToCreate) {
        RunningSubmission runningSubmission = runningSubmissionRepository.getById(id, serviceToCreate);
        if (runningSubmission == null) return null;
        return toOutput(runningSubmission);
    }

    private Output toOutput(RunningSubmission runningSubmission) {
        return Output.builder()
                .coderId(runningSubmission.getCoderId())
                .problemId(runningSubmission.getProblemId())
                .code(runningSubmission.getCode())
                .programmingLanguage(runningSubmission.getProgrammingLanguage())
                .submittedAt(runningSubmission.getSubmittedAt())
                .judgingTestCase(runningSubmission.getJudgingTestCase())
                .totalTestCases(runningSubmission.getTotalTestCases())
                .build();
    }
}
