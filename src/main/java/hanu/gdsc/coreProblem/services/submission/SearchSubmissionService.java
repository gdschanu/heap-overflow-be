package hanu.gdsc.coreProblem.services.submission;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchSubmissionService {
    public List<Submission> get(int page,
                                int perPage,
                                Id problemId,
                                Id coderId,
                                String serviceToCreate);

    public Submission getById(Id id, String serviceToCreate);
}

