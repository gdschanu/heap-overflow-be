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
    public Output submit(Input input) {
        Output output = Output.builder()
            .outputService(submitService.submit(input.inputService))
            .build();
        createSubmissionService.create(
            CreateSubmissionService.Input.builder()
            .problemId(input.inputService.problemId)
            .programmingLanguage(input.inputService.programmingLanguage)
            .runTime(output.outputService.runTime)
            .memory(output.outputService.memory)
            .submittedAt(DateTime.now())
            .code(input.inputService.code)
            .status(output.outputService.status)
            .failedTestCase(output.outputService.failedTestCase)
            .build() 
        );
        return output;
    }
}
