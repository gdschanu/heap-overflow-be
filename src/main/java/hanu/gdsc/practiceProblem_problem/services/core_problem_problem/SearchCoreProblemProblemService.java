package hanu.gdsc.practiceProblem_problem.services.core_problem_problem;

import hanu.gdsc.core_problem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchCoreProblemProblemService {
    private final SearchProblemService searchCoreProblemService;

    public SearchProblemService.Output getById(Id id) {
        return searchCoreProblemService.getById(id, ServiceName.serviceName);
    }

    public List<SearchProblemService.Output> getByIds(List<Id> ids) {
        return searchCoreProblemService.getByIds(ids, ServiceName.serviceName);
    }
}
