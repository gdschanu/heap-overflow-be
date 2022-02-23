package hanu.gdsc.problem.services.submission;

import hanu.gdsc.problem.domains.ID;
import hanu.gdsc.problem.domains.submission.Submission;

public interface GetSubmissionService {
    public Submission getById(ID id);
}
