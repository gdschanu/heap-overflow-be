package hanu.gdsc.practiceProblem.services.problem;

import hanu.gdsc.coreProblem.services.problem.SubmitService;
import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.ProblemRepository;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmitProblemServiceImpl implements SubmitProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private SubmitService submitCoreProblemService;

    @Override
    public SubmitService.Output submit(Input input) {
        Problem practiceProblem = problemRepository.getById(input.problemId);
        if (practiceProblem == null) {
            throw new BusinessLogicError("Problem doesn't exist.", "UNEXIST_PROBLEM");
        }
        return submitCoreProblemService.submit(SubmitService.Input.builder()
                .coderId(input.coderId)
                .problemId(practiceProblem.getCoreProblemId())
                .code(input.code)
                .programmingLanguage(input.programmingLanguage)
                .serviceName(ServiceName.serviceName)
                .build());
    }
}
