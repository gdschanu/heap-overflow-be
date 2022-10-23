package hanu.gdsc.domain.core_problem.repositories;

import java.util.List;

import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.share.models.Id;

public interface ProblemRepository {
    public Problem getById(Id id, String serviceToCreate);

    public void create(Problem problem);

    public void createMany(List<Problem> problems);

    public void update(Problem problem);

    public List<Problem> getByIds(List<Id> ids, String serviceToCreate);

    public void deleteById(Id id);

    public void deleteByIds(List<Id> ids);
}
