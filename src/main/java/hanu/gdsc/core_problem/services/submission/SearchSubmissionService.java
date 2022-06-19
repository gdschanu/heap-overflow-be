package hanu.gdsc.core_problem.services.submission;

import hanu.gdsc.core_problem.domains.*;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.List;

public interface SearchSubmissionService {

    public List<Submission> get(int page,
                                int perPage,
                                Id problemId,
                                Id coderId,
                                String serviceToCreate);

    public Submission getById(Id id, String serviceToCreate);
}

