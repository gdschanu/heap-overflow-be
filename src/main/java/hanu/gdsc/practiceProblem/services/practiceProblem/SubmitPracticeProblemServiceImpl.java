package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.coreProblem.services.problem.SubmitService;
import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmitPracticeProblemServiceImpl implements SubmitPracticeProblemService {
    @Autowired
    private PracticeProblemRepository practiceProblemRepository;

    @Autowired
    private SubmitService submitCoreProblemService;

    @Override
    public SubmitService.Output submit(Input input) {
        Problem practiceProblem = practiceProblemRepository.getById(input.problemId);
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
