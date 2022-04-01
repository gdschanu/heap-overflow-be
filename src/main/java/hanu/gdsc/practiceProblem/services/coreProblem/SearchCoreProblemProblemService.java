package hanu.gdsc.practiceProblem.services.coreProblem;

import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.share.domains.Id;

public interface SearchCoreProblemProblemService {
    SearchProblemService.Output getById(Id id);
}
