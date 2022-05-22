package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class CompleteJudgeTestCaseEventHandler {
    private final Judger judger;
    private final ProblemRepository problemRepository;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final SubmissionEventRepository submissionEventRepository;
    private final CompleteJudgeTestCaseEventQueue completeJudgeTestCaseEventQueue;
    private final StartJudgeTestCaseEventQueue startJudgeTestCaseEventQueue;

    @Scheduled(fixedRate = 1000)
    public void handle() throws IOException, InterruptedException {
        CompleteJudgeTestCaseEvent event = completeJudgeTestCaseEventQueue.get();
        if (event == null) {
            return;
        }
        Judger.Submission judgeSubmission = judger.getSubmissionById(event.judgeSubmissionId);
        if (judgeSubmission.processing() || judgeSubmission.inQueue()) {
            completeJudgeTestCaseEventQueue.publish(event);
            return;
        }

        Problem problem = problemRepository.getById(event.problemId, event.serviceToCreate);
        if (problem == null) {
            return;
        }
        TimeLimit timeLimit = problem.getTimeLimitByProgrammingLanguage(event.programmingLanguage);
        MemoryLimit memoryLimit = problem.getMemoryLimitByProgrammingLanguage(event.programmingLanguage);

        List<TestCase> testCases = testCaseRepository.getByProblemId(event.problemId, event.serviceToCreate);
        if (event.testCaseIndex > testCases.size() - 1) {
            return;
        }
        TestCase testCase = TestCase.sortByOrdinal(testCases).get(event.testCaseIndex);

        Submission submission = null;
        Millisecond avgRunTime = event.totalRuntime
                .plus(judgeSubmission.runTime())
                .divide(event.testCaseIndex + 1);
        KB avgMemLimit = event.totalMemory
                .plus(judgeSubmission.memory())
                .divide(event.testCaseIndex + 1);
        if (judgeSubmission.compilationError()) {
            submission = Submission.createWithId(
                    event.submissionId,
                    event.problemId,
                    event.programmingLanguage,
                    avgRunTime,
                    avgMemLimit,
                    event.code,
                    Status.CE,
                    FailedTestCaseDetail.fromTestCase(
                            0,
                            "",
                            testCase
                    ),
                    event.serviceToCreate,
                    event.coderId,
                    judgeSubmission.compilationMessage()
            );
        } else if (judgeSubmission.stdError()) {
            submission = Submission.createWithId(
                    event.submissionId,
                    event.problemId,
                    event.programmingLanguage,
                    avgRunTime,
                    avgMemLimit,
                    event.code,
                    Status.STDE,
                    FailedTestCaseDetail.fromTestCase(
                            0,
                            "",
                            testCase
                    ),
                    event.serviceToCreate,
                    event.coderId,
                    judgeSubmission.stdMessage()
            );
        } else if (judgeSubmission.memory().greaterThan(memoryLimit.getMemoryLimit())) {
            submission = Submission.createWithId(
                    event.submissionId,
                    event.problemId,
                    event.programmingLanguage,
                    avgRunTime,
                    avgMemLimit,
                    event.code,
                    Status.MLE,
                    FailedTestCaseDetail.fromTestCase(
                            0,
                            judgeSubmission.output().toString(),
                            testCase
                    ),
                    event.serviceToCreate,
                    event.coderId,
                    judgeSubmission.stdMessage()
            );
        } else if (judgeSubmission.runTime().greaterThan(timeLimit.getTimeLimit())) {
            submission = Submission.createWithId(
                    event.submissionId,
                    event.problemId,
                    event.programmingLanguage,
                    avgRunTime,
                    avgMemLimit,
                    event.code,
                    Status.TLE,
                    FailedTestCaseDetail.fromTestCase(
                            0,
                            judgeSubmission.output().toString(),
                            testCase
                    ),
                    event.serviceToCreate,
                    event.coderId,
                    judgeSubmission.stdMessage()
            );
        } else if (!judgeSubmission.output().equals(testCase.getExpectedOutput())) {
            submission = Submission.createWithId(
                    event.submissionId,
                    event.problemId,
                    event.programmingLanguage,
                    avgRunTime,
                    avgMemLimit,
                    event.code,
                    Status.WA,
                    FailedTestCaseDetail.fromTestCase(
                            judgeSubmission.output().differentLine(testCase.getExpectedOutput()),
                            judgeSubmission.output().toString(),
                            testCase
                    ),
                    event.serviceToCreate,
                    event.coderId,
                    judgeSubmission.stdMessage()
            );
        } else {
            submission = Submission.createWithId(
                    event.submissionId,
                    event.problemId,
                    event.programmingLanguage,
                    avgRunTime,
                    avgMemLimit,
                    event.code,
                    Status.AC,
                    null,
                    event.serviceToCreate,
                    event.coderId,
                    judgeSubmission.stdMessage()
            );
        }
        if (event.testCaseIndex == testCases.size() - 1) {
            submissionRepository.create(submission);
            SubmissionEvent submissionEvent = SubmissionEvent.create(
                    event.problemId,
                    submission.getStatus()
            );
            submissionEventRepository.create(submissionEvent);
        } else {
            if (submission.getStatus().equals(Status.AC)) {
                startJudgeTestCaseEventQueue.publish(new StartJudgeTestCaseEvent(
                        event.submissionId,
                        event.testCaseIndex + 1,
                        event.problemId,
                        event.code,
                        event.serviceToCreate,
                        event.programmingLanguage,
                        event.totalRuntime.plus(judgeSubmission.runTime()),
                        event.totalMemory.plus(judgeSubmission.memory()),
                        event.coderId
                ));
            } else {
                submissionRepository.create(submission);
            }
        }
    }
}
