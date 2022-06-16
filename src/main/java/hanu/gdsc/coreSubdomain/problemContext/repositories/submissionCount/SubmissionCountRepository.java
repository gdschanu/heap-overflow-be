package hanu.gdsc.coreSubdomain.problemContext.repositories.submissionCount;

import hanu.gdsc.coreSubdomain.problemContext.domains.SubmissionCount;
import hanu.gdsc.share.domains.Id;

public interface SubmissionCountRepository {
    public void create(SubmissionCount submission);

    public void update(SubmissionCount submission);

    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate);

    public SubmissionCount getByProblemId(Id problemId);
}
