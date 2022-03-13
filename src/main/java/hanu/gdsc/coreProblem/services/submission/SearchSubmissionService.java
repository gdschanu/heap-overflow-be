package hanu.gdsc.coreProblem.services.submission;

import java.util.List;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.share.domains.Id;

public interface SearchSubmissionService {
    public Submission getById(Id id);

    public List<Submission> getAllByProblemId(Id problemId);
}

