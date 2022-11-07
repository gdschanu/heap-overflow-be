package hanu.gdsc.domain.practiceProblem_problem.services.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class SearchPracticeProblemRunningSubmissionService {
    private final SearchRunningSubmissionService
            searchCoreRunningSubmissionService;
    private final ObjectMapper objectMapper;
    private final ProblemRepository problemRepository;

    public SearchPracticeProblemRunningSubmissionService(SearchRunningSubmissionService searchCoreRunningSubmissionService,
                                                         ObjectMapper objectMapper,
                                                         ProblemRepository problemRepository) throws IOException {
        this.searchCoreRunningSubmissionService = searchCoreRunningSubmissionService;
        this.objectMapper = objectMapper;
        this.problemRepository = problemRepository;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public SearchRunningSubmissionService.Output
    getById(Id id) {
        SearchRunningSubmissionService.Output
                rsp = searchCoreRunningSubmissionService.getById(id, PracticeProblemProblemServiceName.serviceName);
        if (rsp == null)
            return null;
        Problem problem = problemRepository.getByCoreProblemProblemId(rsp.problemId);
        if (problem != null)
            rsp.problemId = problem.getId();
        else
            rsp.problemId = null;
        return rsp;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<SearchRunningSubmissionService.Output>
    getRunningSubmissions(Id problemId,
                          Id coderId,
                          int page,
                          int perPage) {
        Id coreProblemId = null;
        if (problemId != null) {
            Problem problem = problemRepository.getById(problemId);
            if (problem != null) {
                coreProblemId = problem.getCoreProblemId();
            }
        }
        List<SearchRunningSubmissionService.Output>
                outputs = searchCoreRunningSubmissionService.getByProblemIdAndCoderId(
                coreProblemId,
                coderId,
                page,
                perPage,
                PracticeProblemProblemServiceName.serviceName
        );
        if (problemId != null) {
            for (SearchRunningSubmissionService.Output
                    output : outputs) {
                output.problemId = problemId;
            }
        }
        return outputs;
    }
}
