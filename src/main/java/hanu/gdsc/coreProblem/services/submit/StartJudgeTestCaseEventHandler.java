package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class StartJudgeTestCaseEventHandler {
    private final Judger judger;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final StartJudgeTestCaseEventQueue startJudgeTestCaseEventQueue;
    private final CompleteJudgeTestCaseEventQueue completeJudgeTestCaseEventQueue;

    @Scheduled(fixedRate = 1000)
    public void handle() throws IOException, InterruptedException {
        StartJudgeTestCaseEvent event = startJudgeTestCaseEventQueue.get();
        if (event == null) {
            return;
        }
        List<TestCase> testCases = testCaseRepository.getByProblemId(event.problemId, event.serviceToCreate);
        if (event.testCaseIndex > testCases.size() - 1) {
            return;
        }
        TestCase testCase = TestCase.getSortedByOrdinalTestCases(testCases).get(event.testCaseIndex);
        String judgeSubmissionId;
        try {
            judgeSubmissionId = judger.createSubmission(event.code, testCase.getInput(), event.programmingLanguage);
        } catch (Throwable e) {
            startJudgeTestCaseEventQueue.publish(event);
            return;
        }
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
            e.printStackTrace();
            startJudgeTestCaseEventQueue.publish(event);
        }
    }
}
