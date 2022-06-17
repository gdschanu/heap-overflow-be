package hanu.gdsc.practiceProblem_problem.services.core_problem_runningSubmission;

import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SearchRunningSubmissionService {
    private final hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService searchCoreRunningSubmissionService;

    public hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output getByIdAndCoderId(Id id, Id coderId) {
        return searchCoreRunningSubmissionService.getByIdAndCoderId(id, coderId, ServiceName.serviceName);
    }

    public List<hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output> getByCoderId(int page, int perPage, Id coderId) {
        return searchCoreRunningSubmissionService.getByCoderId(page, perPage, coderId, ServiceName.serviceName);
    }
}
