package hanu.gdsc.practiceProblem_problem.services.core_problem.submission;

import hanu.gdsc.core_problem.services.submission.SearchSubmissionService;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCoreProblemSubmissionService {
    public List<SearchSubmissionService.Output> get(int page, int perPage, Id problemId, Id coderId);

    public SearchSubmissionService.Output getById(Id id);
}
