package hanu.gdsc.coreProblem.repositories;

import java.util.List;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.share.domains.Id;

public interface ProblemRepository {
    public Problem getById(Id id, String serviceToCreate);

    public void create(Problem problem);

    public void update(Problem problem);

    public List<Problem> getByIds(List<Id> ids, String serviceToCreate);

    public void deleteById(Id id);
}
