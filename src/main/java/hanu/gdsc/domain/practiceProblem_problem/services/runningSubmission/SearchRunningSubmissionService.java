package hanu.gdsc.domain.practiceProblem_problem.services.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

@Service
public class SearchRunningSubmissionService {
    private ServerSocket serverSocket;
    private final hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService
            searchCoreRunningSubmissionService;
    private final ObjectMapper objectMapper;
    private final ProblemRepository problemRepository;

    public SearchRunningSubmissionService(hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService searchCoreRunningSubmissionService,
                                          ObjectMapper objectMapper,
                                          ProblemRepository problemRepository) throws IOException {
        this.searchCoreRunningSubmissionService = searchCoreRunningSubmissionService;
        this.objectMapper = objectMapper;
        this.problemRepository = problemRepository;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output
    getById(Id id) {
        hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output
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
    public List<hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output>
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
        List<hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output>
                outputs = searchCoreRunningSubmissionService.getByProblemIdAndCoderId(
                coreProblemId,
                coderId,
                page,
                perPage,
                PracticeProblemProblemServiceName.serviceName
        );
        if (problemId != null) {
            for (hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output
                    output : outputs) {
                output.problemId = problemId;
            }
        }
        return outputs;
    }
}
