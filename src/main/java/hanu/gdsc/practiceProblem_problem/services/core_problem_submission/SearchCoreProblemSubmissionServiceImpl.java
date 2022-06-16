package hanu.gdsc.practiceProblem_problem.services.core_problem_submission;

import hanu.gdsc.core_problem.services.submission.SearchSubmissionService;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SearchCoreProblemSubmissionServiceImpl implements SearchCoreProblemSubmissionService {
    private final SearchSubmissionService searchSubmissionService;

    @Override
    public List<SearchSubmissionService.Output> get(int page, int perPage, Id problemId, Id coderId) {
        return searchSubmissionService.get(
                page,
                perPage,
                problemId,
                coderId,
                ServiceName.serviceName
        );
    }

    @Override
    public SearchSubmissionService.Output getById(Id id) {
        return searchSubmissionService.getById(id, ServiceName.serviceName);
    }
}
