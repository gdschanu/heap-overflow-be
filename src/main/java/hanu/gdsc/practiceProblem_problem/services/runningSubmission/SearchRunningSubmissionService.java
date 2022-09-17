package hanu.gdsc.practiceProblem_problem.services.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

@Service
public class SearchRunningSubmissionService {
    private ServerSocket serverSocket;
    private final hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService
            searchCoreRunningSubmissionService;
    private final ObjectMapper objectMapper;
    private final ProblemRepository problemRepository;

    public SearchRunningSubmissionService(hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService searchCoreRunningSubmissionService,
                                          ObjectMapper objectMapper,
                                          ProblemRepository problemRepository) throws IOException {
        this.searchCoreRunningSubmissionService = searchCoreRunningSubmissionService;
        this.objectMapper = objectMapper;
        this.problemRepository = problemRepository;
    }

    public hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output
    getById(Id id) {
        hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output
                rsp = searchCoreRunningSubmissionService.getById(id, ServiceName.serviceName);
        if (rsp == null)
            return null;
        Problem problem = problemRepository.getByCoreProblemProblemId(rsp.problemId);
        if (problem != null)
            rsp.problemId = problem.getId();
        else
            rsp.problemId = null;
        return rsp;
    }

    public List<hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output>
    getRunningSubmissions(Id problemId,
                          Id coderId,
                          int page,
                          int perPage) {
        Id coreProblemId = null;
        if (problemId != null) {
            Problem problem = problemRepository.getById(problemId);
            if (problem != null) {
                coreProblemId = problem.getCoreProblemProblemId();
            }
        }
        List<hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output>
                outputs = searchCoreRunningSubmissionService.getByProblemIdAndCoderId(
                coreProblemId,
                coderId,
                page,
                perPage,
                ServiceName.serviceName
        );
        if (problemId != null) {
            for (hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output
                    output : outputs) {
                output.problemId = problemId;
            }
        }
        return outputs;
    }
}
