package hanu.gdsc.practiceProblem.services.coreProblem;

import hanu.gdsc.coreProblem.services.submission.SearchSubmissionService;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCoreProblemSubmissionService {
    public List<SearchSubmissionService.Output> get(int page, int perPage, Id problemId, Id coderId);

    public SearchSubmissionService.Output getById(Id id);
}
