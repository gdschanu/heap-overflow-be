package hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.problem;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.Problem;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ProblemRepository {
    public void create(Problem practiceProblem);

    public Problem getById(Id id);

    public List<Problem> get(int page, int perPage);

    public void update(Problem problem);
}