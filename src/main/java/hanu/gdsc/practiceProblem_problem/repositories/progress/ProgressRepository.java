package hanu.gdsc.practiceProblem_problem.repositories.progress;

import hanu.gdsc.practiceProblem_problem.domains.Progress;
import hanu.gdsc.share.domains.Id;

public interface ProgressRepository {
    public Progress getByCoderId(Id coderId);

    public void save(Progress progress);
}
