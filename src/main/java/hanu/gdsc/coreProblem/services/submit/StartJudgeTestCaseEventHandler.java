package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;
import hanu.gdsc.coreProblem.services.testCasePing.TestCasePingService;
import hanu.gdsc.share.scheduling.Scheduler;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class StartJudgeTestCaseEventHandler {
    private final Judger judger;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final StartJudgeTestCaseEventQueue startJudgeTestCaseEventQueue;
    private final CompleteJudgeTestCaseEventQueue completeJudgeTestCaseEventQueue;
    private final TestCasePingService testCasePingService;

    public StartJudgeTestCaseEventHandler(Judger judger, TestCaseRepository testCaseRepository, SubmissionRepository submissionRepository, StartJudgeTestCaseEventQueue startJudgeTestCaseEventQueue, CompleteJudgeTestCaseEventQueue completeJudgeTestCaseEventQueue, TestCasePingService testCasePingService) {
        this.judger = judger;
        this.testCaseRepository = testCaseRepository;
        this.submissionRepository = submissionRepository;
        this.startJudgeTestCaseEventQueue = startJudgeTestCaseEventQueue;
        this.completeJudgeTestCaseEventQueue = completeJudgeTestCaseEventQueue;
        this.testCasePingService = testCasePingService;
        new Scheduler(100, new Scheduler.Runner() {
            @Override
            public void run() throws IOException, InterruptedException {
                handle();
            }
        }).start();
    }

    private void handle() throws IOException, InterruptedException {
        StartJudgeTestCaseEvent event = startJudgeTestCaseEventQueue.get();
        if (event == null) {
            return;
        }
        List<TestCase> testCases = testCaseRepository.getByProblemId(event.problemId, event.serviceToCreate);
        testCasePingService.ping(event.coderId, TestCasePingService.Payload.builder()
                .currentTestCase(event.testCaseIndex + 1)
                .totalTestCase(testCases.size())
                .submissionId(event.submissionId)
                .build());
        if (event.testCaseIndex > testCases.size() - 1) {
            return;
        }
        TestCase testCase = TestCase.sortByOrdinal(testCases).get(event.testCaseIndex);
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
