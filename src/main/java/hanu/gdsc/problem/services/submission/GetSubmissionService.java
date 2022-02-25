package hanu.gdsc.problem.services.submission;

import hanu.gdsc.share.domains.ID;
import hanu.gdsc.problem.domains.Submission;

public interface GetSubmissionService {
    public Submission getById(ID id);
}
