package hanu.gdsc.practiceProblem_problem.services.core.problem.problem;

import hanu.gdsc.core_problem.services.problem.SearchProblemService;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCoreProblemProblemService {
    SearchProblemService.Output getById(Id id);

    List<SearchProblemService.Output> getByIds(List<Id> ids);
}
