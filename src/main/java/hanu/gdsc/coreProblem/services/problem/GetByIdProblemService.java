package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.share.domains.Id;

public interface GetByIdProblemService {
    public Problem getById(Id id);
}
