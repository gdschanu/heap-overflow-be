package hanu.gdsc.domain.practiceProblem_problem.repositories;

import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.infrastructure.practiceProblem_problem.repositories.problem.ProblemCountProjection;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface ProblemRepository {
    public void create(Problem practiceProblem);

    public Problem getById(Id id);

    public List<Problem> get(int page, int perPage);

    public void update(Problem problem);

    public long countProblem();

    public List<Problem> getRecommendProblem(int count);

    public void deleteById(Id id);

    public Problem getByCoreProblemProblemId(Id coreProblemProblemId);

    public List<Problem> findByCoreProblemProblemIds(List<Id> coreProblemProblemIds);

    public List<ProblemCountProjection> countProblemGroupByDifficulty();
}
