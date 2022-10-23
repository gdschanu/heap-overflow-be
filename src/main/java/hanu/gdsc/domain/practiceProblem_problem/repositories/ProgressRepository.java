package hanu.gdsc.domain.practiceProblem_problem.repositories;

import hanu.gdsc.domain.practiceProblem_problem.domains.Progress;
import hanu.gdsc.domain.share.models.Id;

public interface ProgressRepository {
    public Progress getByCoderId(Id coderId);

    public void save(Progress progress);
}
