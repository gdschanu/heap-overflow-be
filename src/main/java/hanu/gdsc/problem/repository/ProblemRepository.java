package hanu.gdsc.problem.repository;

import hanu.gdsc.problem.domains.Problem;
import hanu.gdsc.share.domains.ID;

public interface ProblemRepository {
    public Problem getById(ID id);
}
