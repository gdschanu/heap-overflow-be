package hanu.gdsc.practiceProblem.services.coreProblem.problem;

import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCoreProblemProblemService {
    SearchProblemService.Output getById(Id id);

    List<SearchProblemService.Output> getByIds(List<Id> ids);
}
