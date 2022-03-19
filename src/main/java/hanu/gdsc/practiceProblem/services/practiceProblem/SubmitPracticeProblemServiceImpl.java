package hanu.gdsc.practiceProblem.services.practiceProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.SubmitService;
import hanu.gdsc.coreProblem.services.submission.CreateSubmissionService;
import hanu.gdsc.share.domains.DateTime;

@Service
public class SubmitPracticeProblemServiceImpl implements SubmitPracticeProblemService{
    @Autowired
    private SubmitService submitService;
    @Autowired
    private CreateSubmissionService createSubmissionService;

    @Override
    public SubmitService.Output submit(Input input) {
        SubmitService.Output output = submitService.submit(input.inputService);
        createSubmissionService.create(
            CreateSubmissionService.Input.builder()
            .problemId(input.inputService.problemId)
            .programmingLanguage(input.inputService.programmingLanguage)
            .runTime(output.runTime)
            .memory(output.memory)
            .submittedAt(DateTime.now())
            .code(input.inputService.code)
            .status(output.status)
            .failedTestCase(output.failedTestCase)
            .build() 
        );
        return output;
    }
}
