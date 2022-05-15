package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class StartJudgeTestCaseEventHandler {
    private final JudgerImpl judgerImpl;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final StartJudgeTestCaseEventQueue startJudgeTestCaseEventQueue;
    private final CompleteJudgeTestCaseEventQueue completeJudgeTestCaseEventQueue;

    public void handle(StartJudgeTestCaseEvent event) throws IOException, InterruptedException {
        startJudgeTestCaseEventQueue.ack(event);
        List<TestCase> testCases = testCaseRepository.getByProblemId(event.problemId, event.serviceToCreate);
        if (event.testCaseIndex > testCases.size() - 1) {
            return;
        }
        TestCase testCase = testCases.get(event.testCaseIndex);
        String judgeSubmissionId = judgerImpl.createSubmission(event.code, testCase.getInput(), event.programmingLanguage);
        try {
            completeJudgeTestCaseEventQueue.publish(new CompleteJudgeTestCaseEvent(
                    judgeSubmissionId,
                    event.problemId,
                    event.serviceToCreate,
                    event.programmingLanguage,
                    event.testCaseIndex,
                    event.submissionId,
                    event.code,
                    event.coderId,
                    event.totalMemory,
                    event.totalRuntime
            ));
        } catch (Throwable e) {
            startJudgeTestCaseEventQueue.publish(event);
        }
    }
}
