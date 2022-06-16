package hanu.gdsc.practiceProblemSubdomain.problemContext.services.coreProblem.submission;

import hanu.gdsc.coreSubdomain.problemContext.services.submission.SearchSubmissionService;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCoreProblemSubmissionService {
    public List<SearchSubmissionService.Output> get(int page, int perPage, Id problemId, Id coderId);

    public SearchSubmissionService.Output getById(Id id);
}
