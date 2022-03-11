package hanu.gdsc.practiceProblem.repositories;

import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.share.domains.Id;

public interface PracticeProblemRepository {
    public void create(Problem practiceProblem);

    public Problem getById(Id id);
}
