package hanu.gdsc.domain.core_problem.services.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.core_problem.models.RunningSubmission;
import hanu.gdsc.domain.core_problem.repositories.RunningSubmissionRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    public List<RunningSubmission> getByProblemIdAndCoderId(Id problemId,
                                                            Id coderId,
                                                            int page,
                                                            int perPage,
                                                            String serviceToCreate) {
        List<RunningSubmission> runningSubmissions = runningSubmissionRepository.getByProblemIdAndCoderId(
                problemId,
                coderId,
                page,
                perPage,
                serviceToCreate
        );
        return runningSubmissions;
    }

    @Override
    public List<RunningSubmission> getByProblemIdsAndCoderId(List<Id> problemIds, Id coderId, int page, int perPage, String serviceToCreate) {
        // TODO
        return null;
    }

    @Override
    public RunningSubmission getById(Id id, String serviceToCreate) {
        RunningSubmission runningSubmission = runningSubmissionRepository.getById(id, serviceToCreate);
        if (runningSubmission == null)
            return null;
        return runningSubmission;
    }

    private RunningSubmission toOutput(RunningSubmission runningSubmission) {
        return runningSubmission;
    }
}
