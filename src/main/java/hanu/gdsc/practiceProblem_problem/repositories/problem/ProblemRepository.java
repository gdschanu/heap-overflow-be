package hanu.gdsc.practiceProblem_problem.repositories.problem;

import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ProblemRepository {
    public void create(Problem practiceProblem);

    public Problem getById(Id id);

    public List<Problem> get(int page, int perPage);

    public void update(Problem problem);

    public long countProblem();

    public List<Problem> getRecommendProblem(int count);

    public void deleteById(Id id);
}
