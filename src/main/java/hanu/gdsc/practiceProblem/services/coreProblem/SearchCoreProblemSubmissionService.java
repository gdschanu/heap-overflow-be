package hanu.gdsc.practiceProblem.services.coreProblem;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCoreProblemSubmissionService {
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId);

    public Submission getById(Id id);
}
