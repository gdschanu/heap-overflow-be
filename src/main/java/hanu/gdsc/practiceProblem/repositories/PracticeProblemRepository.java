package hanu.gdsc.practiceProblem.repositories;

import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface PracticeProblemRepository {
    public void create(Problem practiceProblem);

    public Problem getById(Id id);

    public List<Problem> get(int page, int perPage);
}
